package cz.library.store.security.infrastructure.service.encoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderTest {

  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private BCryptEncoder bCryptEncoder;

  @BeforeEach
  void setup() {
    bCryptPasswordEncoder = new BCryptPasswordEncoder();
    bCryptEncoder = new BCryptEncoder();
  }

  @Test
  void givenPassword_whenEncode_thenSuccess() {
    // given
    String password = "password";

    // when
    String encodedPassword = bCryptEncoder.encode(password);

    // then
    assertTrue(bCryptPasswordEncoder.matches(password, encodedPassword));
  }

  @Test
  void givenRawPasswordAndEncodedPassword_whenMatch_thenSuccess() {
    // given
    String rawPassword = "password";
    String encodedPassword = bCryptEncoder.encode(rawPassword);

    // when
    boolean match = bCryptEncoder.matches(rawPassword, encodedPassword);

    // then
    assertTrue(match);
  }

  @Test
  void givenDifferentRawPasswordAndEncodedPassword_whenMatch_thenFail() {
    // given
    String rawPassword = "password";
    String encodedPassword = bCryptEncoder.encode(rawPassword);

    // when
    boolean match = bCryptEncoder.matches("differentPassword", encodedPassword);

    // then
    assertFalse(match);
  }
}
