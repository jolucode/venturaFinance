package com.vsoluciones.service.impl;

import com.vsoluciones.model.Log;
import com.vsoluciones.repo.IGenericRepo;
import com.vsoluciones.repo.ILogRepo;
import com.vsoluciones.service.ILogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl extends CRUDImpl<Log, String> implements ILogService {

  private final ILogRepo repo;

  @Override
  protected IGenericRepo<Log, String> getRepo() {
    return repo;
  }


  /*@Override
  public Mono<Log> save(Log client) {
    return repo.save(client);
  }

  @Override
  public Mono<Log> udpate(Log client, String id) {
    return repo.findById(id).flatMap(v -> repo.save(client));
  }

  @Override
  public Flux<Log> findAll() {
    return repo.findAll();
  }

  @Override
  public Mono<Log> findById(String id) {
    return repo.findById(id);
  }

  @Override
  public Mono<Boolean> delete(String id) {
    return repo.findById(id)
        .hasElement()
        .flatMap(valor -> {
          if (valor) {
            return repo.deleteById(id).thenReturn(valor);
            //return Mono.just(valor);
          } else {
            return Mono.just(!valor);
          }
        });
  }*/
}
