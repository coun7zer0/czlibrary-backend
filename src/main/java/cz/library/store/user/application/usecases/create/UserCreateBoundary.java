package cz.library.store.user.application.create;

import cz.library.store.user.application.dto.UserRequestData;
import cz.library.store.user.application.dto.UserResponseData;

public interface UserCreateBoundary {
  public UserResponseData create(UserRequestData data);
}
