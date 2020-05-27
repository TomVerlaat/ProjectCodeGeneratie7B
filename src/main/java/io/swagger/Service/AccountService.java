package io.swagger.Service;

import io.swagger.dao.AccountRepository;
import io.swagger.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountService() {
    }

    public List<Account> getAllAccounts()
    {
        return (List<Account>) accountRepository.findAll();
    }

    public List<Account> getAccountsByUserId(Long userId)
    {
        return accountRepository.getAccountByUserId(userId);
    }

    public Account getAccountByIban(String iban)
    {
        return accountRepository.getAccountByIban(iban);
    }

    public void deactivateAccount(String iban)
    {
        accountRepository.deleteAccountBy(iban);
    }
}
