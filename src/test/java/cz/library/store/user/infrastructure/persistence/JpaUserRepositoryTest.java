package cz.library.store.user.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class JpaUserRepositoryTest {

  @Autowired
  private JpaUserRepository userRepository;

  @Test
  void givenExistingEmail_whenExistsByEmail_thenReturnTrue() {
    // given
    String email = "jhon.doe@example.com";

    UserDataMapper user = new UserDataMapper();
    user.setEmail(email);
    userRepository.save(user);

    // when
    boolean result = userRepository.existsByEmail(email);

    // then
    assertTrue(result);
  }

  @Test
  void givenNotExistingEmail_whenExistsByEmail_thenReturnFalse() {
    // given
    String email = "jhon.doe@example.com";

    // when
    Boolean result = userRepository.existsByEmail(email);

    // then
    assertFalse(result);
  }

  @Test
  void givenExistingPhoneNumber_whenExistsByPhoneNumber_thenReturnTrue() {
    // given
    String phoneNumber = "+123456789";

    UserDataMapper user = new UserDataMapper();
    user.setPhoneNumber(phoneNumber);
    userRepository.save(user);

    // when
    Boolean result = userRepository.existsByPhoneNumber(phoneNumber);

    // then
    assertTrue(result);
  }

  @Test
  void givenNotExistingPhoneNumber_whenExistsByPhoneNumber_thenReturnFalse() {
    // given
    String phoneNumber = "+123456789";

    // when
    Boolean result = userRepository.existsByPhoneNumber(phoneNumber);

    // then
    assertFalse(result);
  }

}
