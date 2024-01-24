package com.vsoluciones.service.impl;

import com.vsoluciones.model.Customer;
import com.vsoluciones.repo.ICustomerRepo;
import com.vsoluciones.repo.IGenericRepo;
import com.vsoluciones.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends CRUDImpl<Customer, String> implements ICustomerService {

  private final ICustomerRepo repo;

  @Override
  protected IGenericRepo<Customer, String> getRepo() {
    return repo;
  }


  /*@Override
  public Mono<Customer> save(Customer client) {
    return repo.save(client);
  }

  @Override
  public Mono<Customer> udpate(Customer client, String id) {
    return repo.findById(id).flatMap(v -> repo.save(client));
  }

  @Override
  public Flux<Customer> findAll() {
    return repo.findAll();
  }

  @Override
  public Mono<Customer> findById(String id) {
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
