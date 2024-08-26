package cz.library.store.validation.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cz.library.store.validation.domain.Validator;

public class ValidatorBuilderTest {

  private class MockEntity {

    private String firstField;

    private String secondField;

    public MockEntity() {
    }

    public String getFirstField() {
      return firstField;
    }

    public void setFirstField(String firstField) {
      this.firstField = firstField;
    }

    public String getSecondField() {
      return secondField;
    }

    public void setSecondField(String secondField) {
      this.secondField = secondField;
    }

  }

  private class ValidatorBuilderImpl extends ValidatorBuilder<MockEntity, ValidatorBuilderImpl> {
  }

  private MockEntity entity;
  private ValidatorBuilderImpl validatorBuilder;

  @BeforeEach
  void setUp() {
    entity = new MockEntity();
    validatorBuilder = new ValidatorBuilderImpl();
  }

  @Test
  void givenFieldNotNull_whenValidateFieldNotNull_thenReturnNull() {
    // given
    entity.setFirstField("first");

    Validator<MockEntity> validator = validatorBuilder
        .fieldNotNull(
            "firstField", MockEntity::getFirstField)
        .build();

    // when
    String result = validator.validate(entity);

    // then
    assertNull(result);
  }

  @Test
  void givenFieldNull_whenValidateFieldNotNull_thenReturnErrorMessage() {
    // given
    entity.setFirstField(null);

    Validator<MockEntity> validator = validatorBuilder
        .fieldNotNull(
            "firstField", MockEntity::getFirstField)
        .build();

    // when
    String result = validator.validate(entity);

    // then
    assertEquals("Invalid field firstField. The first field cannot be null.", result);
  }

  @Test
  void givenTwoFieldsNotNull_whenValidateFieldNotNullAnd_thenReturnNull() {
    // given
    entity.setFirstField("first");
    entity.setSecondField("second");
    Validator<MockEntity>validator = validatorBuilder
        .fieldNotNullAnd(Map.of(
            "firstField", MockEntity::getFirstField,
            "secondField", MockEntity::getSecondField))
        .build();

    // when
    String result = validator.validate(entity);

    // then
    assertNull(result);
  }

  @Test
  void givenTwoFieldsFirstFieldNull_whenValidateFieldNotNullAnd_thenReturnErrorMessage() {
    // given
    entity.setFirstField(null);
    entity.setSecondField("second");
    Validator<MockEntity>validator = validatorBuilder
        .fieldNotNullAnd(Map.of(
            "firstField", MockEntity::getFirstField,
            "secondField", MockEntity::getSecondField))
        .build();

    // when
    String result = validator.validate(entity);

    // then
    assertEquals("Invalid field firstField. The first field cannot be null.", result);
  }

  @Test
  void givenTwofieldsFirstFieldNull_whenValidateFieldNotNullOr_thenReturnNull() {
    // given
    entity.setFirstField(null);
    entity.setSecondField("second");
    Validator<MockEntity>validator = validatorBuilder
        .fieldNotNullOr(Map.of(
            "firstField", MockEntity::getFirstField,
            "secondField", MockEntity::getSecondField))
        .build();

    // when
    String result = validator.validate(entity);

    // then
    assertNull(result);
  }

  @Test
  void givenTwoFieldsNull_whenValidateFieldNotNullOr_thenReturnErrorMessage() {
    // given
    entity.setFirstField(null);;
    entity.setSecondField(null);
    Validator<MockEntity>validator = validatorBuilder
        .fieldNotNullOr(Map.of(
            "firstField", MockEntity::getFirstField,
            "secondField", MockEntity::getSecondField))
        .build();

    // when
    String result = validator.validate(entity);

    //then
    assertTrue(result.matches("You must provide either "
            + "(first|second)Field or "
        + "(first|second)Field."));
  }

  @Test
  void givenUniqueField_whenValidateFieldIsUnique_thenReturnNull() {
    // given
    Predicate<String> existsByFirstField = firstField -> false;

    entity.setFirstField("first");
    Validator<MockEntity> validator = validatorBuilder
        .fieldIsUnique("firstField", MockEntity::getFirstField, existsByFirstField)
        .build();

    //when
    String result = validator.validate(entity);

    //then
    assertNull(result);
  }

  @Test
  void givenExistingField_whenValidateFieldIsUnique_thenReturnErrorMessage() {
    // given
    Predicate<String> existsByFirstField = firstField -> true;

    entity.setFirstField("first");
    Validator<MockEntity> validator = validatorBuilder
        .fieldIsUnique("firstField", MockEntity::getFirstField, existsByFirstField)
        .build();

    //when
    String result = validator.validate(entity);

    //then
    assertEquals("Invalid field firstField."
          + " The firstField already exists in the database.", result);
  }

}
