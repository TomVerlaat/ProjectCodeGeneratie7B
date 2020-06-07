package io.swagger.Service;

import io.swagger.dao.AccountRepository;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

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
        try {
            return accountRepository.getAccountByUserId(userId);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public Account getAccountByIban(String iban)
    {
        return accountRepository.getAccountByIban(iban);
    }

    @PutMapping
    public void deactivateAccount(String iban)
    {
        Account accountToDeactivate = accountRepository.getAccountByIban(iban);
        accountToDeactivate.setActive(false);
        accountRepository.save(accountToDeactivate);
    }

    public boolean addAccount(Account account)
    {
        Account checkIfIbanExists = getAccountByIban(account.getIban());
        if (checkIfIbanExists == null){
            accountRepository.save(account);
            return true;
        }
        else {
            return false;
        }

    }

    public void updateAccount(Account account)
    {
        accountRepository.save(account);
    }

    public String GetIban(String iban)
    {
        Account accountToCheck = accountRepository.getAccountByIban(iban);
        return accountToCheck.getIban();
    }

}
