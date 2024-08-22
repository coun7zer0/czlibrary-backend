package cz.library.store.validation.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import cz.library.store.util.StringUtil;
import cz.library.store.validation.domain.Validator;

public class FieldNotNullOr<T, R> extends Validator<T> {

  private final Map<String, Function<T, R>> fields;

  public FieldNotNullOr(Map <String, Function<T, R>> fields) {
    this.fields = new HashMap<>(fields);
  }

  @Override
  public String validate(T entity) {

    for (Function<T, R> fieldGetter : new ArrayList<>(fields.values())) {
      if (fieldGetter.apply(entity) != null) {
        return validateNext(entity);
      }
    }

    List<String> fieldNames = new ArrayList<>(fields.keySet());
    return "You must provide either " + StringUtil.concatListOr(fieldNames) + ".";
  }

}
