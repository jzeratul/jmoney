package org.playground.jmoneyserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class JUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long userid;

  private String username;

  // TODO encrypt when writing
  private String password;

  private LocalDateTime createdAt;

  // TODO see how to build the OneToMany relation with Jar
}
