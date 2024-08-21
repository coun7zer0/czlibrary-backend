package cz.library.store.validation.domain;

import java.util.List;

public abstract class Validator<T> {
  private Validator<T> next;

  public static <T> Validator<T> link(List<Validator<T>> chain) {
    if (chain.isEmpty()) {
      return null;
    }

    Validator<T> first = chain.remove(0);
    Validator<T> head = first;

    for (Validator<T> nextInChain : chain) {
    	head.next = nextInChain;
      head = nextInChain;
    }

    return first;
  }

  public abstract String validate(T entity);

  protected final String validateNext(T entity) {
    if (next == null) {
      return null;
    }
    return next.validate(entity);
  }
}
