package com.vsoluciones.controller;

import com.vsoluciones.dto.InvoiceDTO;
import com.vsoluciones.model.Invoice;
import com.vsoluciones.service.IInvoiceService;
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
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

  private final IInvoiceService service;

  @Qualifier("invoiceMapper")
  private final ModelMapper mapper;

  @GetMapping
  public Mono<ResponseEntity<Flux<InvoiceDTO>>> findAll() {
    Flux<InvoiceDTO> fx = service.findAll()
        .map(this::convertToDto);
    return Mono.just(ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fx))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<InvoiceDTO>> findById(@PathVariable("id") String id) {
    return service.findById(id)
        .map(this::convertToDto)
        .map(x -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(x))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<InvoiceDTO>> save(@Valid @RequestBody InvoiceDTO dto, final ServerHttpRequest req) {
    return service.save(convertToEntity(dto))
        .map(this::convertToDto)
        .map(x -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(x.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(x))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


  @PutMapping("/{id}")
  public Mono<ResponseEntity<InvoiceDTO>> update(@Valid @RequestBody InvoiceDTO dto, @PathVariable("id") String id) {
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

  private InvoiceDTO convertToDto(Invoice model) {
    return mapper.map(model, InvoiceDTO.class);
  }

  private Invoice convertToEntity(InvoiceDTO dto) {
    return mapper.map(dto, Invoice.class);
  }

}
