package cz.library.store.user.application.dto;

import cz.library.store.user.domain.Gender;
import cz.library.store.user.domain.User;

public record UserRequestData(
    String email,
    String password,
    String name,
    String lastname,
    String phoneNumber,
    Gender gender
    ) {
  public User toUser() {
    return new User(null, email(), password(), name(), lastname(), phoneNumber(), gender());
  }
}
