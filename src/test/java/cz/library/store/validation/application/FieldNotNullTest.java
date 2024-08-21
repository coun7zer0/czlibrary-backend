package cz.library.store.validation.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class FieldNotNullTest {

  private class MockEntity {

    private final String firstField;
    private final String secondField;

    public MockEntity(String firstField, String secondField) {
      this.firstField = firstField;
      this.secondField = secondField;;
    }

	  public String getFirstField() {
		  return firstField;
	  }

	  public String getSecondField() {
		  return secondField;
	  }
    
  }

  @Test
  void givenSingleFieldNotNull_whenValidate_thenReturnNull() {
    // given
    MockEntity entity = new MockEntity("firstField", null);
    
    FieldNotNull<MockEntity, String> validator =
        new FieldNotNull<>("firstField", MockEntity::getFirstField);

    // when
    String result = validator.validate(entity);

    // then
    assertEquals(null, result);
  }

  @Test
  void givenTwoFieldsNotNull_whenValidate_thenReturnNull() {
    // given
    MockEntity entity = new MockEntity("firstField", "secondField");
    
    FieldNotNull<MockEntity, String> validator =
        new FieldNotNull<>(Map.of(
            "firstField", MockEntity::getFirstField,
            "secondField", MockEntity::getSecondField));
    
    // when
    String result = validator.validate(entity);
    
    // then
    assertEquals(null, result);
  }

  @Test
  void givenSingleFieldNull_whenValidate_thenReturnErrorMessage() {
    // given
    MockEntity entity = new MockEntity(null, null);

    FieldNotNull<MockEntity, String> validator =
        new FieldNotNull<>("firstField", MockEntity::getFirstField);
    
    // when
    String result = validator.validate(entity);
    
    // then
    assertEquals("Invalid field firstField. The first field cannot be null.", result);
  }

  @Test
  void givenTwoFieldsNull_whenValidate_thenReturnErrorMessage() {
    // given
    MockEntity entity = new MockEntity(null, null);
    
    FieldNotNull<MockEntity, String> validator =
        new FieldNotNull<>(Map.of(
            "firstField", MockEntity::getFirstField,
            "secondField", MockEntity::getSecondField));
    
    // when
    String result = validator.validate(entity);
    
    // then
    assertEquals("Invalid field secondField. The second field cannot be null.", result);
  }

  @Test
  void givenTwoFieldsFirstFieldNull_whenValidate_thenReturnErrorMessage() {
    // given
    MockEntity entity = new MockEntity(null, "secondField");

    FieldNotNull<MockEntity, String> validation =
        new FieldNotNull<>(Map.of(
            "firstField", MockEntity::getFirstField,
            "secondField", MockEntity::getSecondField));

    // when
    String result = validation.validate(entity);

    // then
    assertEquals("Invalid field firstField. The first field cannot be null.", result);
  }

  @Test
  void givenTwoFieldsSecondFieldNull_whenValidate_thenReturnErrorMessage() {
    // given
    MockEntity entity = new MockEntity("firstField", null);

    FieldNotNull<MockEntity, String> validator =
        new FieldNotNull<>(Map.of(
            "firstField", MockEntity::getFirstField,
            "secondField", MockEntity::getSecondField));

    // when
    String result = validator.validate(entity);

    // then
    assertEquals("Invalid field secondField. The second field cannot be null.", result);
  }
}
