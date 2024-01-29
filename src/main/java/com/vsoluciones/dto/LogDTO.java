package com.vsoluciones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogDTO {

  private String id;
  private String ruc;
  private String businessName;
  private String request;
  private String response;
  private String thirdPartyRequestXml;
  private String thirdPartyResponseXml;
  private String requestDate;
  private String responseDate;
  private String thirdPartyServiceInvocationDate;
  private String thirdPartyServiceResponseDate;
  private String objectTypeAndDocEntry;
  private String seriesAndCorrelative;
}
