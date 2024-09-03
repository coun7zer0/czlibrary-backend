package cz.library.store.user.infrastructure.usecase.create;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import cz.library.store.user.application.dto.UserRequestData;
import cz.library.store.user.application.dto.UserResponseData;

@RestController
public class UserCreateController {

  private final UserCreateService userService;

  public UserCreateController(UserCreateService userService) {
    this.userService = userService;
  }

  @PostMapping("/api/v1/users")
  public ResponseEntity<UserResponseData> create(
      @RequestBody UserRequestData request, UriComponentsBuilder uriBuilder) {
    return userService.create(request, uriBuilder);
  }
}
