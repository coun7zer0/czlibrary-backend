package cz.library.store.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

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
    // Name cannot be null, it should contain only letters and optionally one second name
    return name != null
    && name.matches("[A-Za-z]+( [A-Za-z]+)?");
  }

  public boolean lastnameIsVaild() {
    // Lastname cannot be null, it should contain only letters and optionally one second lastname
    return lastname != null
        && lastname.matches("[A-Za-z]+( [A-Za-z]+)?");
  }

  public boolean phoneNumberIsValid() {
    // Phone number with country code validation
    return phoneNumber != null
        && phoneNumber.matches("\\+\\d{1,3}[- .]?\\d{1,14}([-. ]?\\d{1,13})*");
  }
  
}
