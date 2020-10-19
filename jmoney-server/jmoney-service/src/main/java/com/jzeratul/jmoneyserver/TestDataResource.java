package com.jzeratul.jmoneyserver;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jzeratul.jmoneyserver.model.ButtonVariant;
import com.jzeratul.jmoneyserver.model.JUser;
import com.jzeratul.jmoneyserver.model.Jar;
import com.jzeratul.jmoneyserver.model.Payment;
import com.jzeratul.jmoneyserver.repositories.JUserRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@AllArgsConstructor
@Slf4j
//TODO move to ACTUATOR ENDPOINTS
public class TestDataResource {

	private JUserRepo userRepo;

  @GetMapping(path = "/get")
  public JUser get() {
		return userRepo.findById(1L).get();
  }

  @GetMapping(path = "/update")
  public JUser update() {

		JUser user = userRepo.findById(1L).get();

//    final LocalDate now = LocalDate.now();
//    final LocalDateTime now1 = LocalDateTime.now();
//    Jar jar3 = Jar.builder()
//            .name("Freedom")
//            .procent(BigDecimal.TEN)
//            .variant(ButtonVariant.LIGHT.getName())
//            .lastPayments(Arrays.asList(
//                    Payment.builder().reason("Freedom").paymentDate(now).amount(BigDecimal.valueOf(5555)).createdAt(now1).build(),
//                    Payment.builder().reason("Freedom").paymentDate(now).amount(BigDecimal.valueOf(6666)).createdAt(now1).build(),
//                    Payment.builder().reason("Freedom").paymentDate(now).amount(BigDecimal.valueOf(7777)).createdAt(now1).build()))
//            .createdAt(now1)
//            .build();
//
//    user.getJars().removeIf(j -> j.getName().equals("Play"));
//
//    user.getJars().add(jar3);
//
//		user = userRepo.save(user);

    return user;
  }

  @GetMapping(path = "/create")
  public JUser create() {

    JUser user = getjUser1();

		user = userRepo.save(user);

    return user;
  }

  private JUser getjUser1() {

    final LocalDate now = LocalDate.now();

    final LocalDateTime now1 = LocalDateTime.now();

//    Jar jar = Jar.builder()
//            .name("Necessities")
//            .variant(ButtonVariant.DANGER.getName())
//            .procent(BigDecimal.TEN)
//            .lastPayments(Arrays.asList(
//                            Payment.builder().reason("Necessities").paymentDate(now).amount(BigDecimal.valueOf(123)).createdAt(now1).build()))
//            .createdAt(now1)
//            .build();
//
//    Jar jar2 = Jar.builder()
//            .name("Play")
//            .procent(BigDecimal.TEN)
//            .variant(ButtonVariant.PRIMARY.getName())
//            .lastPayments(Arrays.asList(
//                    Payment.builder().reason("Play").paymentDate(now).amount(BigDecimal.valueOf(5555)).createdAt(now1).build()))
//            .createdAt(now1)
//            .build();
//
//    Jar jar3 = Jar.builder()
//            .name("Freedom")
//            .procent(BigDecimal.TEN)
//            .variant(ButtonVariant.SUCCESS.getName())
//            .lastPayments(Arrays.asList(
//                    Payment.builder().reason("Freedom").paymentDate(now).amount(BigDecimal.valueOf(44)).createdAt(now1).build()))
//            .createdAt(now1)
//            .build();

    JUser user = JUser.builder()
            .username("jzeratul")
//            .jars(Arrays.asList(jar, jar2, jar3))
            .createdAt(now1)
            .build();
    return user;
  }

  private JUser getjUser() {

    final LocalDate now = LocalDate.now();

    final LocalDateTime now1 = LocalDateTime.now();

//    Jar jar = Jar.builder()
//            .name("Necessities")
//            .variant(ButtonVariant.DANGER.getName())
//            .procent(BigDecimal.TEN)
//            .lastPayments(Arrays.asList(
//                            Payment.builder().reason("Necessities").paymentDate(now).amount(BigDecimal.valueOf(123)).createdAt(now1).build(),
//                            Payment.builder().reason("Necessities").paymentDate(now).amount(BigDecimal.valueOf(22)).createdAt(now1).build(),
//                            Payment.builder().reason("Necessities").paymentDate(now).amount(BigDecimal.valueOf(11)).createdAt(now1).build(),
//                            Payment.builder().reason("Necessities").paymentDate(now).amount(BigDecimal.valueOf(234)).createdAt(now1).build(),
//                            Payment.builder().reason("Necessities").paymentDate(now).amount(BigDecimal.valueOf(928374)).createdAt(now1).build()))
//            .createdAt(now1)
//            .build();
//
//    Jar jar2 = Jar.builder()
//            .name("Play")
//            .procent(BigDecimal.TEN)
//            .variant(ButtonVariant.PRIMARY.getName())
//            .lastPayments(Arrays.asList(
//                    Payment.builder().reason("Play").paymentDate(now).amount(BigDecimal.valueOf(5555)).createdAt(now1).build(),
//                    Payment.builder().reason("Play").paymentDate(now).amount(BigDecimal.valueOf(6666)).createdAt(now1).build(),
//                    Payment.builder().reason("Play").paymentDate(now).amount(BigDecimal.valueOf(7777)).createdAt(now1).build()))
//            .createdAt(now1)
//            .build();
//
//    Jar jar3 = Jar.builder()
//            .name("Freedom")
//            .procent(BigDecimal.TEN)
//            .variant(ButtonVariant.SUCCESS.getName())
//            .lastPayments(Arrays.asList(
//                    Payment.builder().reason("Freedom").paymentDate(now).amount(BigDecimal.valueOf(44)).createdAt(now1).build(),
//                    Payment.builder().reason("Freedom").paymentDate(now).amount(BigDecimal.valueOf(5)).createdAt(now1).build(),
//                    Payment.builder().reason("Freedom").paymentDate(now).amount(BigDecimal.valueOf(777)).createdAt(now1).build()))
//            .createdAt(now1)
//            .build();

    JUser user = JUser.builder()
            .username("jzeratul")
//            .jars(Arrays.asList(jar, jar2, jar3))
            .createdAt(now1)
            .build();
    return user;
  }
}
