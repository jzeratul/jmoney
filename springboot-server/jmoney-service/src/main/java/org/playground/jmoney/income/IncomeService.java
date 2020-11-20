package org.playground.jmoney.income;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.playground.jmoney.model.WebIncome;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class IncomeService {

  private IncomeRepo repo;
  private IncomeMapper mapper;

  public List<WebIncome> get(long userid) {

    Optional<List<Income>> userIncomes = repo.findByUserid(userid);

    if (userIncomes.isEmpty()) {
      return Collections.emptyList();
    }

    return userIncomes.get().stream()
            .map(j -> mapper.toWebIncome(j))
            .collect(Collectors.toList());
  }

  public WebIncome create(long userid, WebIncome create) {

    log.debug("Creating income {}# amount:{} for user {}", create.getSource(), create.getAmount(), userid);
    Income income = mapper.fromWebIncome(create);
    income.setUserid(userid);

    Income saved = repo.save(income);

    WebIncome webIncome = mapper.toWebIncome(saved);
    log.info("Updated income {}# id:{} for user {}", webIncome.getSource(), webIncome.getId(), userid);

    return webIncome;
  }

  public WebIncome delete(long userid, WebIncome delete) {

    Long id = mapper.decryptId(delete.getId());
    log.debug("Deleting income {}# id:{} for user {}", delete.getSource(), id, userid);

    Optional<Income> byId = repo.findById(id);
    if (byId.isEmpty()) {
      throw new IllegalArgumentException("Requested income to delete does not belong to the requesting user or does not exist.");
    }

    repo.delete(byId.get());
    log.info("Deleted income {}# id:{} for user {}", delete.getSource(), id, userid);

    return delete;
  }

  public WebIncome update(long userid, WebIncome update) {

    log.debug("Updating income {}# id:{} for user {}", update.getSource(), update.getId(), userid);
    Income income = mapper.fromWebIncome(update);

    Optional<Income> byId = repo.findById(income.getIncomeid());
    if (byId.isEmpty()) {
      throw new IllegalArgumentException("Requested income to update does not belong to the requesting user or does not exist");
    }

    repo.save(income);
    log.info("Updated income {}# id:{} for user {}", update.getSource(), update.getId(), userid);

    return update;
  }
}
