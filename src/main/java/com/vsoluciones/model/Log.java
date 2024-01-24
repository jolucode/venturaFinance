package com.vsoluciones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "logs")
public class Log {

  @Id
  @EqualsAndHashCode.Include
  private String id;
  private String ruc;
  private String businessName;
  private String request;
  private String response;
  private String thirdPartyRequestXml;
  private String thirdPartyResponseXml;
  private Date requestDate;
  private Date responseDate;
  private Date thirdPartyServiceInvocationDate;
  private Date thirdPartyServiceResponseDate;

}