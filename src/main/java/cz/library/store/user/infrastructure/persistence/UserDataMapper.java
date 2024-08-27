package cz.library.store.user.infrastructure;

import org.hibernate.annotations.NaturalId;

import cz.library.store.user.domain.Gender;
import cz.library.store.user.domain.User;
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

  public UserDataMapper(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.name = user.getName();
    this.lastname = user.getLastname();
    this.phoneNumber = user.getPhoneNumber();
    this.gender = user.getGender();
  }

  public User toUser() {
    return new User(id, email, password, name, lastname, phoneNumber, gender);
  }
}
