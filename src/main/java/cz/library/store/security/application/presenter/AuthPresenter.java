package cz.library.store.security.application.presenter;

import cz.library.store.security.application.dto.AuthData;

public interface AuthPresenter {

  AuthData prepareSuccessView(AuthData userDetails);

  AuthData prepareFailView(String Message);

}
