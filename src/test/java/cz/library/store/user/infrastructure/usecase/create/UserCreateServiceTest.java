package cz.library.store.user.infrastructure.usecase.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import cz.library.store.user.application.dto.UserRequestData;
import cz.library.store.user.application.dto.UserResponseData;
import cz.library.store.user.application.usecase.create.UserCreateBoundary;
import cz.library.store.user.domain.Gender;
import cz.library.store.user.domain.User;

public class UserCreateServiceTest {

  @Mock
  UserCreateBoundary userInteractor;

  @InjectMocks
  UserCreateService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void givenValidUserData_whenCreate_thenReturnCreatedResponseEntityWithUserAndUri() {
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

    when(userInteractor.create(request)).thenReturn(response);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

    // when
    ResponseEntity<UserResponseData> responseEntity = userService.create(request, uriBuilder);

    // then
    assertEquals(201, responseEntity.getStatusCode().value());
    assertEquals(response, responseEntity.getBody());
    assertEquals(URI.create("api/v1/users/1"), responseEntity.getHeaders().getLocation());
  }

}
