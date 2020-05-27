package io.swagger.dao;

import io.swagger.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> getAllById(long id);

    List<Account> getAccountsBy(long userId);

    Account getAccountBy(String iban);

    void deleteAccountBy(String iban);

    List<Account> getAllById(double value);
}

