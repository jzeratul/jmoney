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
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class JUser {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "juser_sequence")
  @SequenceGenerator(name = "juser_sequence", allocationSize = 1)
  private long id;

  private String username;

  private LocalDateTime createdAt;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Jar> jars;
}
