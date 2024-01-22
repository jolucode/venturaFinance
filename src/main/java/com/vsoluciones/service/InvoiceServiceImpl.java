package com.vsoluciones.service;

import com.vsoluciones.model.Invoice;
import com.vsoluciones.repo.IInvoiceRepo;
import com.vsoluciones.repo.IGenericRepo;
import com.vsoluciones.service.impl.CRUDImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl extends CRUDImpl<Invoice, String> implements IInvoiceService {

  private final IInvoiceRepo repo;

  @Override
  protected IGenericRepo<Invoice, String> getRepo() {
    return repo;
  }


  /*@Override
  public Mono<Invoice> save(Invoice client) {
    return repo.save(client);
  }

  @Override
  public Mono<Invoice> udpate(Invoice client, String id) {
    return repo.findById(id).flatMap(v -> repo.save(client));
  }

  @Override
  public Flux<Invoice> findAll() {
    return repo.findAll();
  }

  @Override
  public Mono<Invoice> findById(String id) {
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
