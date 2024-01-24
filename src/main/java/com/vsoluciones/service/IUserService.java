package com.vsoluciones.service;

import com.vsoluciones.model.User;
import reactor.core.publisher.Mono;

public interface IUserService extends ICRUD<User, String> {
  Mono<User> saveHash(User user);

  Mono<com.vsoluciones.securrity.User> searchByUser(String username);
}
