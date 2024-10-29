package cz.library.store.user.infrastructure.usecase.create;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import cz.library.store.security.application.service.TokenProvider;
import cz.library.store.user.application.dto.UserRequestData;
import cz.library.store.user.application.dto.UserResponseData;
import cz.library.store.user.domain.Gender;
import cz.library.store.user.domain.User;
import cz.library.store.user.infrastructure.persistence.JpaUserRepository;
import cz.library.store.user.infrastructure.persistence.UserDataMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserCreateControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserCreateService userService;

  private User mockUser;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  private JpaUserRepository userRepository;

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
    mockUser = new User(
        1L,
        "jhon.doe@example.com",
        "ValidPassword123!",
        "Jhon",
        "Doe",
        "+123456789",
        Gender.MALE);
  }

  @Test
  void givenValidUserDataAndValidToken_whenCreate_thenReturnCreatedResponseWithLocation()
      throws Exception {
    // given
    userRepository.save(new UserDataMapper(this.mockUser));
    UserRequestData request = new UserRequestData(
        mockUser.getEmail(),
        mockUser.getPassword(),
        mockUser.getName(),
        mockUser.getLastname(),
        mockUser.getPhoneNumber(),
        mockUser.getGender());

    UserResponseData response = new UserResponseData(mockUser);

    String expectedLocation = "/api/v1/users/1";

    when(userService.create(any(UserRequestData.class), any(UriComponentsBuilder.class)))
        .thenReturn(ResponseEntity.created(new URI(expectedLocation)).body(response));

    String token = tokenProvider.generateToken(mockUser, mockUser.getEmail());

    // when
    mockMvc.perform(post("/api/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))

    // then
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(mockUser.getId()))
        .andExpect(jsonPath("$.email").value(mockUser.getEmail()))
        .andExpect(jsonPath("$.name").value(mockUser.getName()))
        .andExpect(jsonPath("$.lastname").value(mockUser.getLastname()))
        .andExpect(jsonPath("$.phoneNumber").value(mockUser.getPhoneNumber()))
        .andExpect(jsonPath("$.gender").value(mockUser.getGender().toString()))
        .andExpect(header().string("Location", expectedLocation));

    verify(userService, times(1)).create(any(UserRequestData.class), any(UriComponentsBuilder.class));
  }

  @Test
  void givenValidUserDataAndInvalidToken_whenCreate_thenReturnUnauthorizedResponse() throws Exception {
    // given
    UserRequestData request = new UserRequestData(
        mockUser.getEmail(),
        mockUser.getPassword(),
        mockUser.getName(),
        mockUser.getLastname(),
        mockUser.getPhoneNumber(),
        mockUser.getGender());

    // Generar un token inválido
    String invalidToken = "invalid.token.here";

    // when
    mockMvc.perform(post("/api/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + invalidToken))

        // then
        .andExpect(status().isForbidden());
  }

}
