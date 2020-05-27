package io.swagger.dao;

import io.swagger.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> getAllById(Long id);

    List<Account> getAccountsById(Long userId);

    List<Account> getAccountByIban(String value);

    void deleteAccountBy(String iban);

    List<Account> getAllById(double value);
}

