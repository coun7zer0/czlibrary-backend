package cz.library.store.security.infrastructure.usecase.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import cz.library.store.security.application.dto.AuthData;
import cz.library.store.security.application.usecase.auth.AuthInteractor;
import cz.library.store.security.infrastructure.model.AuthDetails;
import cz.library.store.user.domain.User;

public class AuthServiceTest {

  @Mock
  private AuthInteractor interactor;

  @InjectMocks
  private AuthService authService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void givenValidIdentifier_whenLoadUserByUsername_thenReturnsExistingUserDetails() {
    // given
    String email = "jhon.doe@example.com";

    User user = new User();
    user.setEmail(email);
    Function<User, String> usernameGetter = User::getEmail;

    UserDetails expected = new AuthDetails(user, usernameGetter);

    when(interactor.auth(email)).thenReturn(new AuthData(user, usernameGetter));

    // when
    UserDetails actual = authService.loadUserByUsername(email);

    // then
    assertEquals(expected.getUsername(), actual.getUsername());
  }
}
