package com.vsoluciones.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {

  private String id;

  @NotNull
  @NotEmpty
  @NotBlank
  @Size(min = 3)
  private String nameDish;

  @Min(value = 1)
  @Max(value = 999)
  private Double priceDish;

  private boolean statusDish;
}
