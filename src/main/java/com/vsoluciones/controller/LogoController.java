package com.vsoluciones.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/logos")
@RequiredArgsConstructor
public class LogoController {

    Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping(value = "/{ruc}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Mono<ResponseEntity<byte[]>> getCompanyLogo(@PathVariable String ruc) {
        String filePath = "C:/clientes/config/" + ruc + "/COMPANY_LOGO.jpg";

        return Mono.fromCallable(() -> {
                    Path path = Paths.get(filePath);
                    if (Files.exists(path)) {
                        byte[] imageBytes = Files.readAllBytes(path);
                        return ResponseEntity
                                .ok()
                                .contentType(MediaType.IMAGE_JPEG)
                                .body(imageBytes);
                    } else {
                        logger.warn("Logo not found for RUC: {}", ruc);
                        return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(new byte[0]); // cuerpo vacío, pero tipo correcto
                    }
                })
                .subscribeOn(Schedulers.boundedElastic());
    }


}
