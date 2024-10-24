package cz.library.store.security.application.usecase.login;

import cz.library.store.security.application.dto.TokenResponseData;

public interface LoginPresenter {

  TokenResponseData prepareSuccessView(TokenResponseData tokenResponseData);

  TokenResponseData prepareFailView(String message);

}
