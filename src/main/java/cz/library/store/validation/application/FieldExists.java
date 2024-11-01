package cz.library.store.validation.application;

import java.util.function.Function;
import java.util.function.Predicate;

import cz.library.store.validation.domain.Validator;

public class FieldExists<T, R> extends Validator<T> {

  private final String fieldName;

  public final Function<T, R> fieldGetter;

  private final Predicate<R> existByField;

  public FieldExists(
      String fieldName,
      Function<T, R> fieldGetter,
      Predicate<R> existByField) {
    this.fieldName = fieldName;
    this.fieldGetter = fieldGetter;
    this.existByField = existByField;
  }

  @Override
  public String validate(T entity) {
    R fieldValue = fieldGetter.apply(entity);

    if (existByField.negate().test(fieldValue)) {
      return "Invalid field " + fieldName + "."
          + " The " + fieldName + " does not exist in the database.";
    }

    return validateNext(entity);
  }
}
