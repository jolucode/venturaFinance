package com.vsoluciones.repo;

import com.vsoluciones.model.User;
import com.vsoluciones.repo.IGenericRepo;
import reactor.core.publisher.Mono;

public interface IUserRepo extends IGenericRepo<User, String> {

    //@Query("{username: ?}")
    Mono<User> findOneByUsername(String username);
}
