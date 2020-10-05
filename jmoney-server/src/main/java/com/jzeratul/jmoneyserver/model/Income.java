package com.jzeratul.jmoneyserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Income {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "income_sequence")
  @SequenceGenerator(name = "income_sequence", allocationSize = 1)
  private Long id;

  @OneToMany
  private Set<Jar> jars;

  private LocalDate incomeDate;
  private LocalDateTime createdAt;
}
