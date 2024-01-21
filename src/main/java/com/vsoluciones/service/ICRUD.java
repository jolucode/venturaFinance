package com.vsoluciones.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICRUD<T, ID> {

  Mono<T> save(T t);

  Mono<T> udpate(T t, ID id);

  Flux<T> findAll();

  Mono<T> findById(ID id);

  Mono<Boolean> delete(ID id);


}
