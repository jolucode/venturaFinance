package com.vsoluciones.service.impl;

import com.vsoluciones.model.Dish;
import com.vsoluciones.repo.IDishRepo;
import com.vsoluciones.repo.IGenericRepo;
import com.vsoluciones.service.IDishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishServiceImpl extends CRUDImpl<Dish, String> implements IDishService {

  private final IDishRepo repo;

  @Override
  protected IGenericRepo<Dish, String> getRepo() {
    return repo;
  }


  /*@Override
  public Mono<Dish> save(Dish dish) {
    return repo.save(dish);
  }

  @Override
  public Mono<Dish> udpate(Dish dish, String id) {
    return repo.findById(id).flatMap(v -> repo.save(dish));
  }

  @Override
  public Flux<Dish> findAll() {
    return repo.findAll();
  }

  @Override
  public Mono<Dish> findById(String id) {
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
