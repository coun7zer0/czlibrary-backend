package cz.library.store.validation.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class FieldIsUniqueTest {

  public class MockEntity {

    public String field;

    public MockEntity(String field) {
      this.field = field;
    }

    public String getField() {
      return field;
    }

  }

  @Test
  void givenNotExistingField_whenValidate_thenReturnNull() {
    // given
    MockEntity entity = new MockEntity("FieldUnique");
    Predicate<String> findByField = field -> false;
    FieldIsUnique<MockEntity, String> validator = new FieldIsUnique<>(
        "field", MockEntity::getField, findByField);

    // when
    String result = validator.validate(entity);

    // then
    assertNull(result);
  }

  @Test
  void givenExistingField_whenValidate_thenReturnErrorMessage() {
    // given
    MockEntity entity = new MockEntity("FieldUnique");
    Predicate<String> findByField = field -> true;
    FieldIsUnique<MockEntity, String> validator = new FieldIsUnique<>(
        "field", MockEntity::getField, findByField);

    // when
    String result = validator.validate(entity);

    // then
    assertEquals("Invalid field field. The field already exists in the database.", result);
  }
}
