package com.vsoluciones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "clients")
public class Client {
  @Id
  @EqualsAndHashCode.Include
  private String id;
  @Field
  private String firstName;
  @Field
  private String lastName;
  @Field
  private LocalDate birthDate;
  @Field
  private String urlPhoto;
}
