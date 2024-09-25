package cz.library.store.security.application.usecase.auth;

import cz.library.store.security.application.dto.AuthData;

public interface AuthBoundary {
  AuthData auth(String identifier);
}
