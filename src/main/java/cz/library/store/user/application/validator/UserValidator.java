package cz.library.store.user.application.validator;

import cz.library.store.user.domain.User;

public abstract class UserValidator {
  private UserValidator next;

  public static UserValidator link(
      UserValidator first, UserValidator... chain) {
    UserValidator head = first;

    for (UserValidator nextInChain : chain) {
      head.next = nextInChain;
      head = nextInChain;
    }
    
    return first;
  }

  public abstract String validate(User user);

  protected String validateNext(User user) {
    if (next == null) {
      return null;
    }
    
    return next.validate(user);
  }
}
