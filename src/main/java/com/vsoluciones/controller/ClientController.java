package com.vsoluciones.controller;

import com.vsoluciones.dto.ClientDTO;
import com.vsoluciones.model.Client;
import com.vsoluciones.service.IClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

  private final IClientService service;

  @Qualifier("clientMapper")
  private final ModelMapper mapper;

  @GetMapping
  public Mono<ResponseEntity<Flux<ClientDTO>>> findAll() {
    Flux<ClientDTO> fx = service.findAll()
        .map(this::convertToDto);
    return Mono.just(ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fx))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<ClientDTO>> findById(@PathVariable("id") String id) {
    return service.findById(id)
        .map(this::convertToDto)
        .map(x -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(x))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<ClientDTO>> save(@Valid @RequestBody ClientDTO dto, final ServerHttpRequest req) {
    return service.save(convertToEntity(dto))
        .map(this::convertToDto)
        .map(x -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(x.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(x))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


  @PutMapping("/{id}")
  public Mono<ResponseEntity<ClientDTO>> update(@Valid @RequestBody ClientDTO dto, @PathVariable("id") String id) {
    return Mono.just(dto)
        .map(x -> {
          x.setId(id);
          return x;
        })
        .flatMap(x -> service.udpate(convertToEntity(dto), id))
        .map(this::convertToDto)
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

  private ClientDTO convertToDto(Client model) {
    return mapper.map(model, ClientDTO.class);
  }

  private Client convertToEntity(ClientDTO dto) {
    return mapper.map(dto, Client.class);
  }

}
