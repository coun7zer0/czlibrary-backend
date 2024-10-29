package cz.library.store.security.infrastructure.usecase.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cz.library.store.user.domain.User;
import cz.library.store.user.infrastructure.persistence.JpaUserRepository;
import cz.library.store.user.infrastructure.persistence.UserDataMapper;

public class JpaLoginTest {

  @Mock
  private JpaUserRepository userRepository;

  @InjectMocks
  private JpaLogin loginDataSource;

  private User mockUser;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    mockUser = new User();
    mockUser.setEmail("jhon.doe@example.com");
    mockUser.setPhoneNumber("+123456789");
  }

  @Test
  void givenExistingEmail_whenFindByUsername_thenReturnUser() {
    // given
    String username = "jhon.doe@example.com";

    when(userRepository.findByEmail("jhon.doe@example.com"))
        .thenReturn(Optional.of(new UserDataMapper(mockUser)));

    // when
    User user = loginDataSource.findByUsername(username);

    // then
    assertEquals(mockUser.getEmail(), user.getEmail());
  }

  @Test
  void givenExistingPhoneNumber_whenFindByUsername_thenReturnUser() {
    // given
    String username = "+123456789";

    when(userRepository.findByPhoneNumber("+123456789"))
        .thenReturn(Optional.of(new UserDataMapper(mockUser)));

    // when
    User user = loginDataSource.findByUsername(username);

    // then
    assertEquals(mockUser.getPhoneNumber(), user.getPhoneNumber());
  }

  @Test
  void givenUnexistingUsername_whenFindByUsername_thenRetunNull() {
    // given
    String username = "InvalidUser";

    when(userRepository.findByEmail(anyString()))
        .thenReturn(Optional.empty());

    when(userRepository.findByEmail(anyString()))
        .thenReturn(Optional.empty());

    // when
    User user = loginDataSource.findByUsername(username);

    // then
    assertNull(user);
  }

  @Test
  void givenExistingEmail_whenExistsByUser_thenReturnTrue() {
    // given
    String username = "jhon.doe@example.com";

    when(userRepository.existsByEmail("jhon.doe@example.com")).thenReturn(true);

    // when
    boolean exists = loginDataSource.existsByUsername(username);

    // then
    assertTrue(exists);
  }

  @Test
  void givenExistingPhoneNumber_whenExistsByUser_thenReturnTrue() {
    // given
    String username = "+123456789";

    when(userRepository.existsByPhoneNumber("+123456789")).thenReturn(true);

    // when
    boolean exists = loginDataSource.existsByUsername(username);

    // then
    assertTrue(exists);
  }

  @Test
  void givenUnexistingUsername_whenExistsByUser_thenReturnFalse() {
    // given
    String username = "InvalidUsername";

    when(userRepository.existsByEmail(anyString())).thenReturn(false);
    when(userRepository.existsByPhoneNumber(anyString())).thenReturn(false);

    // when
    boolean exists = loginDataSource.existsByUsername(username);

    // then
    assertFalse(exists);
  }

}
