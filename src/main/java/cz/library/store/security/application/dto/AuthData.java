package cz.library.store.security.application.dto;

import java.util.function.Function;

import cz.library.store.user.domain.User;

public record AuthData (User user, Function<User, String> usernameGetter) {
}
