package cz.library.store.security.application.usecase.login;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.security.application.dto.TokenResponseData;

public interface LoginBoundary {
  TokenResponseData login(TokenRequestData tokenData);
}
