package com.vsoluciones.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/document")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentsController {


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('SUPPORT')")
    @GetMapping("/download")
    public Mono<ResponseEntity<Resource>> downloadFile(@RequestParam String filePath) {
        return Mono.fromCallable(() -> {
            Path path = Paths.get(filePath);
            Resource resource = new FileSystemResource(path);

            // Verificar si el archivo existe y es legible
            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("El archivo no existe o no es legible: " + filePath);
            }

            String fileName = path.getFileName().toString();
            String contentType = determineContentType(fileName);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        }).onErrorResume(e -> {
            // Manejo del error: Devolver 404 con un mensaje de error
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null)); // También podrías enviar un mensaje en el cuerpo si lo prefieres
        });
    }

    private String determineContentType(String fileName) {
        if (fileName.endsWith(".pdf")) {
            return MediaType.APPLICATION_PDF_VALUE;
        } else if (fileName.endsWith(".xml")) {
            return MediaType.APPLICATION_XML_VALUE;
        } else if (fileName.endsWith(".zip")) {
            return "application/zip";
        }
        return MediaType.APPLICATION_OCTET_STREAM_VALUE; // Valor por defecto para tipos desconocidos
    }



}
