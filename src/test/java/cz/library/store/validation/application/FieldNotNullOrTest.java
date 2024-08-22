package cz.library.store.validation.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class FieldNotNullOrTest {

  private class MockEntity {

    private String firstField;

    private String secondField;

    private Integer integerField;

  private MockEntity(String firstField, String secondField, Integer integerField) {
      this.firstField = firstField;
      this.secondField = secondField;
    this.integerField = integerField;
    }

    public String getFirstField() {
      return firstField;
    }

    public String getSecondField() {
      return secondField;
    }

    public Integer getIntegerField() {
      return integerField;
    }

  }

  @Test
  void givenTwoFieldsNotNull_whenValidate_thenReturnNull() {
    // given
    MockEntity entity = new MockEntity("firstField", "secondField", null);
    FieldNotNullOr<MockEntity, String> validator = new FieldNotNullOr<>(Map.of(
        "firstField", MockEntity::getFirstField,
        "secondField", MockEntity::getSecondField));

    // when
    String result = validator.validate(entity);

    // then
    assertEquals(null, result);;
  }

  @Test
  void givenTwoFieldsNotNullWithDiferentDataType_whenValidate_thenReturnNull() {
    // given
    MockEntity entity = new MockEntity("firstField", null, 1);
    FieldNotNullOr<MockEntity, Object> validator = new FieldNotNullOr<>(Map.of(
        "firstField", MockEntity::getFirstField,
        "integerField", MockEntity::getIntegerField));

    // when
    String result = validator.validate(entity);

    // then
    assertEquals(null, result);
  }

  @Test
  void givenThreeFieldsFirstNotNull_whenValidate_thenReturnNull() {
    // given
    MockEntity entity = new MockEntity("firstField", null, null);
    FieldNotNullOr<MockEntity, Object> validator = new FieldNotNullOr<>(Map.of(
        "firstField", MockEntity::getFirstField,
        "secondField", MockEntity::getSecondField,
        "integerField", MockEntity::getIntegerField));

    // when
    String result = validator.validate(entity);

    // then
    assertEquals(null, result);
  }

  @Test
  void givenThreeFieldsSecondNotNull_whenValidate_thenReturnNull() {
    // given
    MockEntity entity = new MockEntity(null, "secondField", null);
    FieldNotNullOr<MockEntity, Object> validator = new FieldNotNullOr<>(Map.of(
        "firstField", MockEntity::getFirstField,
        "secondField", MockEntity::getSecondField,
        "integerField", MockEntity::getIntegerField));

    // when
    String result = validator.validate(entity);

    // then
    assertEquals(null, result);
  }

  @Test
  void givenThreeFieldsThirdNotNull_whenValidate_thenReturnNull() {
    // given
    MockEntity entity = new MockEntity(null, null, 1);
    FieldNotNullOr<MockEntity, Object> validator = new FieldNotNullOr<>(Map.of(
        "firstField", MockEntity::getFirstField,
        "secondField", MockEntity::getSecondField,
        "integerField", MockEntity::getIntegerField));

    // when
    String result = validator.validate(entity);

    // then
    assertEquals(null, result);
  }

  @Test
  void givenThreeFieldsNull_whenValidate_thenReturnErrorMessage() {
    // given
    MockEntity entity = new MockEntity(null, null, null);
    FieldNotNullOr<MockEntity, Object> validator = new FieldNotNullOr<>(Map.of(
        "firstField", MockEntity::getFirstField,
        "secondField", MockEntity::getSecondField,
        "integerField", MockEntity::getIntegerField));

    // when
    String result = validator.validate(entity);

    //then
    assertEquals("You must provide either firstField, integerField or secondField.", result);
  }

}
