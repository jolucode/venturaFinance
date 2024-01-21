package com.vsoluciones.controller;

import com.vsoluciones.dto.DishDTO;
import com.vsoluciones.dto.DishRecord;
import com.vsoluciones.model.Dish;
import com.vsoluciones.service.IDishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

  private final IDishService service;

  @GetMapping
  public Mono<ResponseEntity<Flux<DishRecord>>> findAll() {
    Flux<DishRecord> fx = service.findAll()
        .map(e -> new DishRecord(e.getId(),e.getName(), e.getPrice(), e.getStatus()));
    return Mono.just(ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fx))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Dish>> findById(@PathVariable("id") String id) {
    return service.findById(id)
        .map(x -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(x))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<Dish>> save(@RequestBody Dish dish, final ServerHttpRequest req) {
    return service.save(dish)
        .map(x -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(x.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(x))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


  @PutMapping("/{id}")
  public Mono<ResponseEntity<Dish>> update(@RequestBody Dish dish, @PathVariable("id") String id) {
    return Mono.just(dish)
        .map(x -> {
          x.setId(id);
          return x;
        })
        .flatMap(x -> service.udpate(dish, id))
        .map(e -> ResponseEntity.ok()
            .body(e)
        )
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Object>> eliminar(@PathVariable("id") String id) {
    return service.delete(id)
        .flatMap(result -> {
          if (result) {
            return Mono.just(ResponseEntity.noContent().build());
          }
          return Mono.just(ResponseEntity.notFound().build());
        })
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
