package cz.library.store.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UserTest {

  // Test for email validation
  @Test
  public void givenValidEmail_whenEmailIsValid_thenIsTrue() {
    String emailContent = "example@example.com";
    User user = new User();
    
    user.setEmail(emailContent);

    assertThat(user.getEmail()).isEqualTo(emailContent);
    assertThat(user.emailIsValid()).isTrue();
  }

  @Test
  public void givenNullEmail_whenEmailIsInvalid_thenIsFalse() {
    String emailContent = null;
    User user = new User();
    
    user.setEmail(emailContent);
    
    assertThat(user.getEmail()).isEqualTo(emailContent);
    assertThat(user.emailIsValid()).isFalse();
  }

  @Test
  public void givenBlankEmail_whenEmailIsInvalid_thenIsFalse() {
    String emailContent = "";
    User user = new User();
    
    user.setEmail(emailContent);
    
    assertThat(user.getEmail()).isEqualTo(emailContent);
    assertThat(user.emailIsValid()).isFalse();
  }

  @Test
  public void givenEmailWithMissingAtSymbol_whenEmailIsInvalid_thenIsFalse() {
    String emailContent = "example.com";
    User user = new User();
    
    user.setEmail(emailContent);

    assertThat(user.getEmail()).isEqualTo(emailContent);
    assertThat(user.emailIsValid()).isFalse();
  }

  @Test
  public void givenEmailWithMissingLocalPart_whenEmailIsInvalid_thenIsFalse() {
    String emailContent = "@example.com";
    User user = new User();
    
    user.setEmail("@example.com");

    assertThat(user.getEmail()).contains(emailContent);
    assertThat(user.emailIsValid()).isFalse();
  }

  @Test
  public void givenEmailWithInvalidDomain_whenEmailIsInvalid_thenIsFalse() {
    String emailContent = "example@com";
    User user = new User();
    
    user.setEmail(emailContent);

    assertThat(user.getEmail()).isEqualTo(emailContent);
    assertThat(user.emailIsValid()).isFalse();
  }
  
  // Test for password validation    
  @Test
  public void givenValidPassword_whenPasswordIsValid_thenIsTrue() {
    String passwordContent = "Password123!";
    User user = new User();
    
    user.setPassword(passwordContent);

    assertThat(user.getPassword()).isEqualTo(passwordContent);
    assertThat(user.passwordIsValid()).isTrue();
  }

  @Test
  public void givenNullPassword_whenPasswordIsInvalid_thenIsFalse() {
    String passwordContent = null;
    User user = new User();

    user.setPassword(passwordContent);
    
    assertThat(user.getPassword()).isEqualTo(null);
    assertThat(user.passwordIsValid()).isFalse();
  }

  @Test
  public void givenBlankPassword_whenPasswordIsInvalid_thenIsFalse() {
    String passwordContent = "";
    User user = new User();
    
    user.setPassword(passwordContent);
    
    assertThat(user.getPassword()).isEqualTo("");
    assertThat(user.passwordIsValid()).isFalse();
  }
  
  @Test
  public void givenShortPassword_whenPasswordIsInvalid_thenIsFalse() {
    String passwordContent = "Pd123!";
    User user = new User();
      
    user.setPassword(passwordContent);

    assertThat(user.getPassword()).isEqualTo(passwordContent);
    assertThat(user.passwordIsValid()).isFalse();
  }
  
  @Test
  public void givenPasswordWithNoUppercaseCharacter_whenPasswordIsInvalid_thenIsFalse() {
    String passwordContent = "nouppercase1!";
    User user = new User();
    
    user.setPassword(passwordContent);

    assertThat(user.getPassword()).isEqualTo(passwordContent);
    assertThat(user.passwordIsValid()).isFalse();
  }
  
  @Test
  public void givenPasswordWithNoLowercaseCharacter_whenPasswordIsInvalid_thenIsFalse() {
    String passwordContent = "NOLOWERCASE1!";
    User user = new User();
      
    user.setPassword(passwordContent);

    assertThat(user.getPassword()).isEqualTo(passwordContent);
    assertThat(user.passwordIsValid()).isFalse();
  }
    
  @Test
  public void givenPasswordWithNoSpecialCharacter_whenPasswordIsInvalid_thenIsFalse() {
    String passwordContent = "NoSpecialChar1";
    User user = new User();
      
    user.setPassword(passwordContent);

    assertThat(user.getPassword()).isEqualTo(passwordContent);
    assertThat(user.passwordIsValid()).isFalse();
  }
  
  @Test
  public void givenPasswordWithNoNumber_whenPasswordIsInvalid_thenIsFalse() {
    String passwordContent = "NoSpecialNumber!";
    User user = new User();
      
    user.setPassword(passwordContent);

    assertThat(user.getPassword()).isEqualTo(passwordContent);
    assertThat(user.passwordIsValid()).isFalse();
  }

  // Tests for name validation
  @Test
  public void givenUniqueName_whenNameIsValid_thenIsTrue() {
    String nameContent = "John";
    User user = new User();
      
    user.setName(nameContent);

    assertThat(user.getName()).isEqualTo(nameContent);
    assertThat(user.nameIsValid()).isTrue();
  }
  
  @Test
  public void givenTwoNames_whenNameIsValid_thenIsTrue() {
    String nameContent = "John Doe";
    User user = new User();
      
    user.setName(nameContent);

    assertThat(user.getName()).isEqualTo(nameContent);
    assertThat(user.nameIsValid()).isTrue();
  }
  
  @Test
  public void givenNullName_whenNameIsInvalid_thenIsFalse() {
    String nameContent = null;
    User user = new User();
      
    user.setName(nameContent);

    assertThat(user.getName()).isEqualTo(nameContent);
    assertThat(user.nameIsValid()).isFalse();
  }
    
  @Test
  public void givenBlakName_whenNameIsInvalid_thenIsFalse() {
    String nameContent = "";
    User user = new User();
      
    user.setName(nameContent);

    assertThat(user.getName()).isEqualTo(nameContent);
    assertThat(user.nameIsValid()).isFalse();
  }
  
  @Test
  public void givenNameWithLeadingSpace_whenNameIsInvalid_thenIsFalse() {
    String nameContent = " John";
    User user = new User();
      
    user.setName(nameContent);

    assertThat(user.getName()).isEqualTo(nameContent);
    assertThat(user.nameIsValid()).isFalse();
  }
    
  @Test
  public void givenNameWithTrailingSpace_whenNameIsInvalid_thenIsFalse() {
    String nameContent = "John ";
    User user = new User();
      
    user.setName(nameContent);

    assertThat(user.getName()).isEqualTo(nameContent);
    assertThat(user.nameIsValid()).isFalse();
  }
    
  @Test
  public void givenNameWithMultipleSpaces_whenNameIsInvalid_thenIsFalse() {
    String nameContent = "John  Doe";
    User user = new User();
      
    user.setName(nameContent);

    assertThat(user.getName()).isEqualTo(nameContent);
    assertThat(user.nameIsValid()).isFalse();
  }
  
  @Test
  public void givenNameWithNonAlphanumericCharacters_whenNameIsInvalid_thenIsFalse() {
    String nameContent = "John1";
    User user = new User();
      
    user.setName(nameContent);

    assertThat(user.getName()).isEqualTo(nameContent);
    assertThat(user.nameIsValid()).isFalse();

    nameContent = "Jhon!";

    user.setName(nameContent);

    assertThat(user.getName()).isEqualTo(nameContent);
    assertThat(user.nameIsValid()).isFalse();
  }

  // Test for lastname validation
  @Test
  public void givenUniqueLastname_whenLastnameIsValid_thenIsTrue() {
    String lastnameContent = "John";
    User user = new User();
      
    user.setLastname(lastnameContent);

    assertThat(user.getLastname()).isEqualTo(lastnameContent);
    assertThat(user.lastnameIsValid()).isTrue();
  }
  
  @Test
  public void givenTwoLastnames_whenLastnameIsValid_thenIsTrue() {
    String lastnameContent = "John Doe";
    User user = new User();
      
    user.setLastname(lastnameContent);

    assertThat(user.getLastname()).isEqualTo(lastnameContent);
    assertThat(user.lastnameIsValid()).isTrue();
  }
  
  @Test
  public void givenNullLastname_whenLastnameIsInvalid_thenIsFalse() {
    String lastnameContent = null;
    User user = new User();
      
    user.setLastname(lastnameContent);

    assertThat(user.getLastname()).isEqualTo(lastnameContent);
    assertThat(user.lastnameIsValid()).isFalse();
  }
    
  @Test
  public void givenBlakLastname_whenLastnameIsInvalid_thenIsFalse() {
    String lastnameContent = "";
    User user = new User();
      
    user.setLastname(lastnameContent);

    assertThat(user.getLastname()).isEqualTo(lastnameContent);
    assertThat(user.lastnameIsValid()).isFalse();
  }
  
  @Test
  public void givenLastnameWithLeadingSpace_whenLastnameIsInvalid_thenIsFalse() {
    String lastnameContent = " John";
    User user = new User();
      
    user.setLastname(lastnameContent);

    assertThat(user.getLastname()).isEqualTo(lastnameContent);
    assertThat(user.lastnameIsValid()).isFalse();
  }
    
  @Test
  public void givenLastnameWithTrailingSpace_whenLastnameIsInvalid_thenIsFalse() {
    String lastnameContent = "John ";
    User user = new User();
      
    user.setLastname(lastnameContent);

    assertThat(user.getLastname()).isEqualTo(lastnameContent);
    assertThat(user.lastnameIsValid()).isFalse();
  }
    
  @Test
  public void givenLastnameWithMultipleSpaces_whenLastnameIsInvalid_thenIsFalse() {
    String lastnameContent = "John  Doe";
    User user = new User();
      
    user.setLastname(lastnameContent);

    assertThat(user.getLastname()).isEqualTo(lastnameContent);
    assertThat(user.lastnameIsValid()).isFalse();
  }
  
  @Test
  public void givenLastnameWithNonAlphanumericCharacters_whenLastnameIsInvalid_thenIsFalse() {
    String lastnameContent = "John1";
    User user = new User();
      
    user.setLastname(lastnameContent);

    assertThat(user.getLastname()).isEqualTo(lastnameContent);
    assertThat(user.lastnameIsValid()).isFalse();

    lastnameContent = "Jhon!";

    user.setLastname(lastnameContent);

    assertThat(user.getLastname()).isEqualTo(lastnameContent);
    assertThat(user.lastnameIsValid()).isFalse();
  }

  // Test for phoneNumber validation
  @Test
  public void givenPhoneNumberWithMinLength_whenPhoneNumberIsValid_thenIsTrue() {
    String phoneNumberContent = "+753190";
    User user = new User();

    user.setPhoneNumber(phoneNumberContent);

    assertThat(user.getPhoneNumber()).isEqualTo(phoneNumberContent);
    assertThat(user.phoneNumberIsValid()).isTrue();
  }

  @Test
  public void givenPhoneNumberWithMaxLength_whenPhoneNumberIsValid_thenIsTrue() {
    String phoneNumberContent = "+75319024686420";
    User user = new User();

    user.setPhoneNumber(phoneNumberContent);

    assertThat(user.getPhoneNumber()).isEqualTo(phoneNumberContent);
    assertThat(user.phoneNumberIsValid()).isTrue();
  }

  @Test
  public void givenNullPhoneNumber_whenPhoneNumberIsInvalid_thenIsFalse() {
    String phoneNumberContent = null;
    User user = new User();

    user.setPhoneNumber(phoneNumberContent);

    assertThat(user.getPhoneNumber()).isNull();
    assertThat(user.phoneNumberIsValid()).isFalse();
  }

  @Test
  public void givenPhoneNumberWithSpaces_whenPhoneNumberIsInvalid_thenIsFalse() {
    String phoneNumberContent = "+75 319 024 68";
    User user = new User();

    user.setPhoneNumber(phoneNumberContent);

    assertThat(user.getPhoneNumber()).isEqualTo(phoneNumberContent);
    assertThat(user.phoneNumberIsValid()).isFalse();
  }
  
  @Test
  public void givenPhoneNumberWithDashes_whenPhoneNumberIsInvalid_thenIsFalse() {
    String phoneNumberContent = "+75-319-024-68";
    User user = new User();

    user.setPhoneNumber(phoneNumberContent);

    assertThat(user.getPhoneNumber()).isEqualTo(phoneNumberContent);
    assertThat(user.phoneNumberIsValid()).isFalse();
  }
  
  @Test
  public void givenPhoneNumberWithDots_whenPhoneNumberIsInvalid_thenIsFalse() {
    String phoneNumberContent = "+75.319.024.68";
    User user = new User();

    user.setPhoneNumber(phoneNumberContent);

    assertThat(user.getPhoneNumber()).isEqualTo(phoneNumberContent);
    assertThat(user.phoneNumberIsValid()).isFalse();
  }

  @Test
  public void givenPhoneNumberWithoutPlus_whenPhoneNumberIsInvalid_thenIsFalse() {
    String phoneNumberContent = "7531902468";
    User user = new User();

    user.setPhoneNumber(phoneNumberContent);

    assertThat(user.getPhoneNumber()).isEqualTo(phoneNumberContent);
    assertThat(user.phoneNumberIsValid()).isFalse();
  }

  @Test
  public void givenPhoneNumberTooShort_whenPhoneNumberIsInvalid_thenIsFalse() {
    String phoneNumberContent = "+75319";
    User user = new User();

    user.setPhoneNumber(phoneNumberContent);

    assertThat(user.getPhoneNumber()).isEqualTo(phoneNumberContent);
    assertThat(user.phoneNumberIsValid()).isFalse();
  }

  @Test
  public void givenPhoneNumberTooLong_whenPhoneNumberIsInvalid_thenIsFalse() {
    String phoneNumberContent = "+753191232121235";
    User user = new User();

    user.setPhoneNumber(phoneNumberContent);

    assertThat(user.getPhoneNumber()).isEqualTo(phoneNumberContent);
    assertThat(user.phoneNumberIsValid()).isFalse();
  }

  @Test
  public void givenPhoneWithInvalidCharacters_whenPhoneNumberIsInvalid_thenIsFalse() {
    String phoneNumberContent = "+123abc1465";
    User user = new User();

    user.setPhoneNumber(phoneNumberContent);

    assertThat(user.getPhoneNumber()).isEqualTo(phoneNumberContent);
    assertThat(user.phoneNumberIsValid()).isFalse();
  }

}
