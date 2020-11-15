package org.playground.jmoney.jar;

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
import java.time.OffsetDateTime;

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
  private JarType variant;

  // TODO see how to build the OneToOne relation with User
  private long userid;

  private OffsetDateTime createdAt;

  // TODO see how to build the OneToMany relation with Payment
}
