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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import cz.library.store.user.application.dto.UserRequestData;
import cz.library.store.user.application.dto.UserResponseData;
import cz.library.store.user.domain.Gender;
import cz.library.store.user.domain.User;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserCreateControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserCreateService userService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void givenValidUserData_whenCreate_thenReturnCreatedResponseWithLocation()
      throws Exception {
    // given
    User user = new User(
        1L, "jhon.doe@example.com", "ValidPassword123!", "Jhon", "Doe", "+123456789", Gender.MALE);

    UserRequestData request = new UserRequestData(
        user.getEmail(),
        user.getPassword(),
        user.getName(),
        user.getLastname(),
        user.getPhoneNumber(),
        user.getGender());

    UserResponseData response = new UserResponseData(user);

    String expectedLocation = "/api/v1/users/1";

    when(userService.create(any(UserRequestData.class), any(UriComponentsBuilder.class)))
        .thenReturn(ResponseEntity.created(new URI(expectedLocation)).body(response));

    // when
    mockMvc.perform(post("/api/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))

    // then
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.email").value("jhon.doe@example.com"))
        .andExpect(jsonPath("$.name").value("Jhon"))
        .andExpect(jsonPath("$.lastname").value("Doe"))
        .andExpect(jsonPath("$.phoneNumber").value("+123456789"))
        .andExpect(jsonPath("$.gender").value("MALE"))
        .andExpect(header().string("Location", expectedLocation));

    verify(userService, times(1)).create(any(UserRequestData.class), any(UriComponentsBuilder.class));
  }

}
