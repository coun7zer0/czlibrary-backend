package cz.library.store.user.infrastructure.usecase.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cz.library.store.user.domain.User;
import cz.library.store.user.infrastructure.persistence.JpaUserRepository;
import cz.library.store.user.infrastructure.persistence.UserDataMapper;

public class JpaUserCreateTest {

  @Mock
  private JpaUserRepository userRepository;

  private JpaUserCreate userDataSource;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    userDataSource = new JpaUserCreate(userRepository);
  }

  @Test
  void givenUserInstance_whenSaveUser_thenReturnSavedUser() {
    // given
    User user = new User();
    user.setEmail("jhon.doe@example.com");
    user.setPhoneNumber("+1561035123");
    user.setPassword("validPassword123!");

    UserDataMapper userData = new UserDataMapper(user);
    userData.setId(1L);

    when(userRepository.save(any(UserDataMapper.class))).thenReturn(userData);

    // when
    User userSaved = userDataSource.save(user);

    // then
    assertEquals(userData.getId(), userSaved.getId());
    assertEquals(userData.getEmail(), userSaved.getEmail());
    assertEquals(userData.getPhoneNumber(), userSaved.getPhoneNumber());
    assertEquals(userData.getPassword(), userSaved.getPassword());
  }

  @Test
  void givenExistingEmail_whenExistsByEmail_thenReturnTrue() {
    // given
    String email = "jhon.doe@example.com";
    when(userRepository.existsByEmail(email)).thenReturn(true);

    // when
    Boolean result = userDataSource.existsByEmail(email);

    // then
    assertTrue(result);
  }

  @Test
  void givenNonExistingEmail_whenExistsByEmail_thenReturnFalse() {
    // given
    String email = "jhon.doe@example.com";
    when(userRepository.existsByEmail(email)).thenReturn(false);

    // when
    Boolean result = userDataSource.existsByEmail(email);

    // then
    assertFalse(result);
  }

  @Test
  void givenExistingPhoneNumber_whenExistsByPhoneNumber_thenReturnTrue() {
    // given
    String phoneNumber = "+1561035123";
    when(userRepository.existsByPhoneNumber(phoneNumber)).thenReturn(true);

    // when
    Boolean result = userDataSource.existsByPhoneNumber(phoneNumber);

    // then
    assertTrue(result);
  }

  @Test
  void givenNonExistingPhoneNumber_whenExistsByPhoneNumber_thenReturnFalse() {
    // given
    String phoneNumber = "+1561035123";
    when(userRepository.existsByPhoneNumber(phoneNumber)).thenReturn(false);

    // when
    Boolean result = userDataSource.existsByPhoneNumber(phoneNumber);

    // then
    assertFalse(result);
  }

}
