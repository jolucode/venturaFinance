package com.vsoluciones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

  private String id;
  private String ruc;
  @NotEmpty(message = "El nombre de la empresa no puede ser vacio")
  private String businessName;
  private String phone;
  private String email;
  private boolean status;
}
