package cz.library.store.user.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserDataMapper, Long> {

  Boolean existsByEmail(String email);

  Boolean existsByPhoneNumber(String phoneNumber);

  Optional<UserDataMapper> findByEmail(String email);

  Optional<UserDataMapper> findByPhoneNumber(String phoneNumber);

}
