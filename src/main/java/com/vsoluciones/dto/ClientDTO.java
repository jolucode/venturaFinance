package com.vsoluciones.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
