package cz.library.store.user.application.dto;

import cz.library.store.user.domain.Gender;
import cz.library.store.user.domain.User;

public record UserResponseData(
    Long id,
    String email,
    String name,
    String lastname,
    String phoneNumber,
    Gender gender) {
  public UserResponseData (User user) {
    this(
        user.getId(),
        user.getEmail(),
        user.getName(),
        user.getLastname(),
        user.getPhoneNumber(),
        user.getGender());
  }
}
