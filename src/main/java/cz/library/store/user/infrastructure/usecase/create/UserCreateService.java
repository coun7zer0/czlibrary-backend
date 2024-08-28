package cz.library.store.user.infrastructure.usecase.create;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import cz.library.store.user.application.dto.UserRequestData;
import cz.library.store.user.application.dto.UserResponseData;
import cz.library.store.user.application.usecase.create.UserCreateBoundary;

@Service
public class UserCreateService {

  private final UserCreateBoundary userInteractor;

  public UserCreateService (UserCreateBoundary userInteractor) {
    this.userInteractor = userInteractor;
  }

  public ResponseEntity<UserResponseData> create (
      UserRequestData request, UriComponentsBuilder uriBuilder) {
    UserResponseData userCreated = userInteractor.create(request);
    URI uri = uriBuilder.path("api/v1/users/{id}").buildAndExpand(userCreated.id()).toUri();

    return ResponseEntity.created(uri).body(userCreated);
  }

}
