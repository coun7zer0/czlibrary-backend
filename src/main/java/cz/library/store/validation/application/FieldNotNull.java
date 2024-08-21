package cz.library.store.validation.application;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import cz.library.store.util.StringUtil;
import cz.library.store.validation.domain.Validator;

public class FieldNotNull<T, R> extends Validator<T> {

  private final Map<String, Function<T, R>> fields;

  public FieldNotNull(Map<String, Function<T, R>> fields) {
    this.fields = new HashMap<>(fields);
  }

  public FieldNotNull (String fieldName, Function<T, R> fieldGetter) {
    fields = new HashMap<>();
    fields.put(fieldName, fieldGetter);
  }

  @Override
  public String validate(T entity) {
    for (Map.Entry<String, Function<T, R>> field : fields.entrySet()) {
      String fieldName = field.getKey();
      Function<T, R> fieldGetter = field.getValue();

      if (fieldGetter.apply(entity) == null) {
        return "Invalid field " + fieldName + ". "
            + "The " + StringUtil.camelCaseToWords(fieldName) + " cannot be null.";
      }
    }

    return validateNext(entity);
  }

}
