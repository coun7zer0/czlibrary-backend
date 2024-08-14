package cz.library.store.user.application.usecase.create;

import cz.library.store.user.application.dto.UserRequestData;
import cz.library.store.user.application.dto.UserResponseData;

public interface UserCreateBoundary {
  public UserResponseData create(UserRequestData data);
}
