package cz.library.store.validation.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class FieldExistsTest {

  private class MockEntity {

    private String field;

    public MockEntity(String field) {
      this.field = field;
    }

    public String getField() {
        return field;
    }

  }

  @Test
  void givenExistingFild_whenValidated_thenReturnNull() {
    MockEntity entity = new MockEntity("existingField");
    Predicate<String> existByField = field -> true;
    FieldExists<MockEntity, String> validator = new FieldExists<>(
        "field", MockEntity::getField, existByField);

    // when
    String result = validator.validate(entity);

    // then
    assertNull(result);
  }

  @Test
  void givenNonExistingFild_whenValidated_thenReturnErrorMessage() {
    // given
    MockEntity entity = new MockEntity("nonExistingField");
    Predicate<String> existByField = field -> false;
    FieldExists<MockEntity, String> validator = new FieldExists<>(
        "field", MockEntity::getField, existByField);

    // when
    String result = validator.validate(entity);

    // then
    assertEquals("Invalid field field. The field does not exist in the database.", result);
  }

}
