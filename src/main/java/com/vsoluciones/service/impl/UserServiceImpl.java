package com.vsoluciones.service.impl;

import com.vsoluciones.model.User;
import com.vsoluciones.repo.IGenericRepo;
import com.vsoluciones.repo.IRoleRepo;
import com.vsoluciones.repo.IUserRepo;
import com.vsoluciones.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, String> implements IUserService {

  private final IUserRepo repo;
  private final IRoleRepo rolRepo;
  private final BCryptPasswordEncoder bcrypt;

  @Override
  protected IGenericRepo<User, String> getRepo() {
    return repo;
  }

  @Override
  public Mono<User> saveHash(User user) {
    user.setPassword(bcrypt.encode(user.getPassword()));
    return repo.save(user);
  }

  @Override
  public Mono<com.vsoluciones.securrity.User> searchByUser(String username) {
    Mono<User> monoUser = repo.findOneByUsername(username);
    List<String> roles = new ArrayList<>();

    return monoUser.flatMap(u -> {
      return Flux.fromIterable(u.getRoles())
          .flatMap(rol -> {
            return rolRepo.findById(rol.getId())
                .map(r -> {
                  roles.add(r.getName());
                  return r;
                });
          }).collectList().flatMap(list -> {
            u.setRoles(list);
            return Mono.just(u);
          });
    }).flatMap(u -> Mono.just(new com.vsoluciones.securrity.User(u.getUsername(), u.getPassword(), u.isStatus(), roles)));
  }
}