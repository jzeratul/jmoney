package org.playground.jmoneyserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Jar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long jarid;

  private String name;
  private BigDecimal percent;

  @Enumerated(EnumType.STRING)
  private ButtonVariant variant;

  // TODO see how to build the OneToOne relation with User
  private long userid;

  private LocalDateTime createdAt;

  // TODO see how to build the OneToMany relation with Payment
}
