package cz.library.store.security.infrastructure.model;

import java.util.Collection;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cz.library.store.user.domain.User;

public class AuthDetails implements UserDetails {

  private final User user;
  private final Function<User, String> usernameGetter;

  public AuthDetails(User user, Function<User, String> usernameGetter) {
    this.user = user;
    this.usernameGetter = usernameGetter;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.usernameGetter.apply(user);
  }

}
