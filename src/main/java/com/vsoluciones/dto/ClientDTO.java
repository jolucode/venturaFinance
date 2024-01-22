package com.vsoluciones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

  private String id;

  @NotNull
  private String nameClient;

  @NotNull
  private String surnameClient;

  @NotNull
  private LocalDate birthDateClient;

  private String urlPhotoClient;
}
