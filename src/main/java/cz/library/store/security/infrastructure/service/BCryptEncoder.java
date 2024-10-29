package cz.library.store.security.infrastructure.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cz.library.store.security.application.service.PasswordEncoder;

public class BCryptEncoder implements PasswordEncoder {

  public final BCryptPasswordEncoder bCryptPasswordEncoder;

  public BCryptEncoder() {
    this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public String encode(String rawPassword) {
    return bCryptPasswordEncoder.encode(rawPassword);
  }

  @Override
  public boolean matches(String rawPassword, String encodedPassword) {
    return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
  }

}
