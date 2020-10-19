package com.jzeratul.jmoneyserver.web.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
// TODO move to swagger
public class AddPayment {

  private long jarId;
  private long id;

  private String reason;
  private BigDecimal amount;

  private LocalDate paymentDate;
  private LocalDateTime createdAt;
}
