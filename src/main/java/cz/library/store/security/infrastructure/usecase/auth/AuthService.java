package cz.library.store.security.infrastructure.usecase.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cz.library.store.security.application.dto.AuthData;
import cz.library.store.security.application.usecase.auth.AuthBoundary;
import cz.library.store.security.infrastructure.model.UserAuth;

@Service
public class AuthService implements UserDetailsService {

  private final AuthBoundary interactor;

  public AuthService(AuthBoundary interactor) {
    this.interactor = interactor;
  }

  @Override
  public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
    AuthData data = interactor.auth(identifier);
    return new UserAuth(data.user(), data.usernameGetter());
  }

}
