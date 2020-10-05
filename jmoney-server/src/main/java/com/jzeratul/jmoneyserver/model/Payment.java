package com.jzeratul.jmoneyserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
  @SequenceGenerator(name = "payment_sequence", allocationSize = 1)
  private long id;

  private String reason;
  private BigDecimal amount;

  private LocalDate paymentDate;
  private LocalDateTime createdAt;
}
