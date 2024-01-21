package com.vsoluciones.service.impl;

import com.vsoluciones.model.Client;
import com.vsoluciones.repo.IClientRepo;
import com.vsoluciones.repo.IGenericRepo;
import com.vsoluciones.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends CRUDImpl<Client, String> implements IClientService {

  private final IClientRepo repo;

  @Override
  protected IGenericRepo<Client, String> getRepo() {
    return repo;
  }


  /*@Override
  public Mono<Client> save(Client client) {
    return repo.save(client);
  }

  @Override
  public Mono<Client> udpate(Client client, String id) {
    return repo.findById(id).flatMap(v -> repo.save(client));
  }

  @Override
  public Flux<Client> findAll() {
    return repo.findAll();
  }

  @Override
  public Mono<Client> findById(String id) {
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
