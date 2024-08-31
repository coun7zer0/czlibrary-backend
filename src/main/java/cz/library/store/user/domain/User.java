package cz.library.store.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private Long id;
  private String email;
  private String password;
  private String name;
  private String lastname;
  private String phoneNumber;
  private Gender gender;

  public boolean emailIsValid() {
    // RFC 2822 compliant email validation
    String emailRegex = "[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+"
        + "(?:\\.[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+)*"
        + "@(?:[A-Za-z0-9]"
        + "(?:[-A-Za-z0-9]*[A-Za-z0-9])?\\.)+"
        + "[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?";

    return email != null
        && email.matches(emailRegex);
  }

  public boolean passwordIsValid() {
    // At least 8 characters, at least one digit, one uppercase, one lowercase,
    // and one special character
    return password != null
        && password.length() >= 8
        && password.matches(".*\\d.*")
        && password.matches(".*[a-z].*")
        && password.matches(".*[A-Z].*")
        && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
  }

  public boolean nameIsValid() {
    // Name cannot be null, must be capitalized, contain only letters, and may optionally include a second name
    return name != null
    && name.matches("[A-Z][a-z]+( [A-Z][a-z]+)?");
  }

  public boolean lastnameIsValid() {
    // Lastname cannot be null, must be capitalized, contain only letters and may optionally include a second lastname
    return lastname != null
        && lastname.matches("[A-Z][a-z]+( [A-Z][a-z]+)?");
  }

  public boolean phoneNumberIsValid() {
    // E.164 phone number validation (Min 7 digits, max 15 digits, starting with +)
    return phoneNumber != null
        && phoneNumber.matches("\\+\\d{6,14}$");
  }

}
