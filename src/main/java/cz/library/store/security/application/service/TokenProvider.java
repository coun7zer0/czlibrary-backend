package cz.library.store.security.application.service;

import cz.library.store.user.domain.User;

public interface TokenProvider {

  String generateToken(User user, String username);

  boolean validateToken(String token);

  String getSubject(String token);

  Long getUserIdFromJWT(String token);
}
