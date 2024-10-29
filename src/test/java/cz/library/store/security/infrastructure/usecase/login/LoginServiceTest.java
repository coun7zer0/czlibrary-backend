package cz.library.store.security.infrastructure.usecase.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.security.application.dto.TokenResponseData;
import cz.library.store.security.application.usecase.login.LoginInteractor;

public class LoginServiceTest {

  @Mock
  private LoginInteractor interactor;

  @InjectMocks
  private LoginService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void givenValidTokenRequestData_whenLogin_thenReturnOkResponseEntityWithTokenResponseData() {
    // given
    TokenRequestData requestData = new TokenRequestData("validUsername", "vadilPassword123!");
    TokenResponseData expectedToken = new TokenResponseData("token");

    when(interactor.login(requestData)).thenReturn(expectedToken);

    // when
    ResponseEntity<TokenResponseData> actualToken = service.login(requestData);

    // then
    assertEquals(200, actualToken.getStatusCode().value());
    assertEquals(expectedToken, actualToken.getBody());
  }

}
