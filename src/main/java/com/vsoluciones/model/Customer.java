package com.vsoluciones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "customers")
public class Customer {

  @Id
  @EqualsAndHashCode.Include
  private String id;
  private String ruc;
  private String businessName;
  private String phone;
  private String email;
  private boolean status;

}
