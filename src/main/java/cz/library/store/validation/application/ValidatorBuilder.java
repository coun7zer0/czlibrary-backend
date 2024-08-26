package cz.library.store.validation.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import cz.library.store.validation.domain.Validator;

public abstract class ValidatorBuilder<T, U extends ValidatorBuilder<T, U>> {

  private List<Validator<T>> validatorChain;

  @SuppressWarnings("unchecked")
  protected U self() {
    return (U) this;
  }

  public ValidatorBuilder() {
    validatorChain = new ArrayList<>();
  }

  public <R> U fieldNotNull(String fieldName, Function<T, R> fieldGetter) {
    validatorChain.add(new FieldNotNull<>(fieldName, fieldGetter));
    return self();
  }

  public <R> U fieldNotNullAnd(Map<String, Function<T, R>> fields) {
    validatorChain.add(new FieldNotNull<>(fields));
    return self();
  }

  public <R> U fieldNotNullOr(Map<String, Function<T, R>> fields) {
    validatorChain.add(new FieldNotNullOr<>(fields));
    return self();
  }

  public <R> U fieldIsUnique(
      String fieldName, Function<T, R> fieldGetter, Predicate<R> fieldExistBy) {
    validatorChain.add(new FieldIsUnique<>(fieldName, fieldGetter, fieldExistBy));
    return self();
  }

  public Validator<T> build (){
    return Validator.link(validatorChain);
  }

}
