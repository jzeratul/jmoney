package org.playground.jmoney.user;

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
import java.time.OffsetDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class JUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userid;

  private String username;

  // TODO encrypt when writing
  private String password;

  private OffsetDateTime createdAt;

  // TODO see how to build the OneToMany relation with Jar
}
