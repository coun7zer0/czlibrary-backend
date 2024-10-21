package cz.library.store.security.infrastructure.filters.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cz.library.store.security.infrastructure.model.AuthDetails;
import cz.library.store.user.domain.Gender;
import cz.library.store.user.domain.User;
import cz.library.store.user.infrastructure.persistence.JpaUserRepository;
import cz.library.store.user.infrastructure.persistence.UserDataMapper;

public class JpaJwtFilterTest {

  @Mock
  private JpaUserRepository jpaUserRepository;

  @InjectMocks
  private JpaJwtFilter jpaJwtFilter;

  private User mockUser;

  @BeforeEach
  void setUp() {
    mockUser = new User(
        1L,
        "jhon.doe@example.com",
        "validPassword123!",
        "Jhon",
        "Doe",
        "+6551315793",
        Gender.MALE);

    MockitoAnnotations.openMocks(this);
  }

  @Test
  void givenExistingIdAndEmail_whenFindById_thenReturnAuthDetails() {
    // given
    Optional<UserDataMapper> optionalUser = Optional.of(new UserDataMapper(mockUser));
    when(jpaUserRepository.findById(1L)).thenReturn(optionalUser);

    // when
    Optional<AuthDetails> authDetails = jpaJwtFilter.findById(1L, "jhon.doe@example.com");

    // then
    assertTrue(authDetails.isPresent());
    assertEquals(mockUser.getEmail(), authDetails.get().getUsername());
    assertEquals(mockUser.getPassword(), authDetails.get().getPassword());
  }

  @Test
  void givenExistingIdAndPhoneNumber_whenFindById_thenReturnAuthDetails() {
    // given
    Optional<UserDataMapper> optionalUser = Optional.of(new UserDataMapper(mockUser));
    when(jpaUserRepository.findById(1L)).thenReturn(optionalUser);

    // when
    Optional<AuthDetails> authDetails = jpaJwtFilter.findById(1L, "+6551315793");

    // then
    assertTrue(authDetails.isPresent());
    assertEquals(mockUser.getPhoneNumber(), authDetails.get().getUsername());
    assertEquals(mockUser.getPassword(), authDetails.get().getPassword());
  }

  @Test
  void givenInexitingId_whenFindById_thenReturnEmpty() {
    // given
    when(jpaUserRepository.findById(2L)).thenReturn(Optional.empty());

    // when
    Optional<AuthDetails> authDetails = jpaJwtFilter.findById(2L, "jhon.doe@example.com");

    // then
    assertTrue(authDetails.isEmpty());
  }

  @Test
  void givenExistingIdAndWrongUsername_whenFindById_thenReturnEmpty() {
    // given
    when(jpaUserRepository.findById(1L)).thenReturn(Optional.of(new UserDataMapper(mockUser)));

    // when
    Optional<AuthDetails> authDetails = jpaJwtFilter.findById(1L, "wrongUsername");

    // then
    assertTrue(authDetails.isEmpty());
  }

}
