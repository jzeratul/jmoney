package com.jzeratul.jmoneyserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Jar {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jar_sequence")
  @SequenceGenerator(name = "jar_sequence", allocationSize = 1)
  private long id;

  private String name;
  private BigDecimal procent;
  private String variant;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Payment> lastPayments;

  private LocalDateTime createdAt;
}
