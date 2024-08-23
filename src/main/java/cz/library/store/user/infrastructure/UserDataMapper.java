package cz.library.store.user.infrastructure;

import org.hibernate.annotations.NaturalId;

import cz.library.store.user.domain.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDataMapper {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  @NaturalId
  private String email;

  private String password;

  private String name;

  private String lastname;

  @Column(unique = true)
  @NaturalId
  private String phoneNumber;

  @Enumerated(value = EnumType.STRING)
  private Gender gender;
}
