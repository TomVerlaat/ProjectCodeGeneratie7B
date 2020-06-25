package io.swagger.Service;

import io.swagger.dao.AccountRepository;
import io.swagger.model.Account;
import io.swagger.model.NewAccountBody;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    public AccountService() {}

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


    public ResponseEntity addAccountResponseEntity(NewAccountBody body) {
        Account account = new Account();
        account.setBalance(0);
        account.setActive(true);
        account.setCurrency(body.getCurrency());
        account.setIban(generateRandomIban());
        account.setType(body.getType());

        account.setUserId(body.getUserId());
        if (addAccount(account)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(account.getId());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(account.getIban());
        }
    }

    public ResponseEntity deactivateAccountResponseEntity(String iban){
        if (isUserEmployee()) {
            Account accountToDeactivate = getAccountByIban(iban);
            if (accountToDeactivate == null) {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } else {
                deactivateAccount(iban);
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity getAccountByIbanResponseEntity(String iban){
        Account account = getAccountByIban(iban);
        if (account == null){
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(account);
        }
        else{
            return ResponseEntity
                    .status(200)
                    .body(account);
        }
    }

    public ResponseEntity getByCurrentUserResponseEntity(){
        List<Account> accounts;
        accounts = getAccountsByUserId(userService.getUserId());
        if (accounts.size() > 0) {
            return ResponseEntity
                    .status(200)
                    .body(accounts);
        } else {
            return ResponseEntity
                    .status(204)
                    .body(accounts);
        }
    }

    public ResponseEntity getByUserIdResponseEntity(long userId){
        List<Account> accounts;
        if (isUserEmployee()) {
            accounts = getAccountsByUserId(userId);
            if (accounts.size() > 0) {
                return ResponseEntity
                        .status(200)
                        .body(accounts);
            } else {
                return ResponseEntity
                        .status(204)
                        .body(accounts);
            }
        }
        else {
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity getAllAccountsResponseEntity() {
        List<Account> accounts = null;
        if (isUserEmployee()) {
            accounts = getAllAccounts();
            if (accounts.size() > 0) {
                return ResponseEntity
                        .status(200)
                        .body(accounts);
            } else {
                return ResponseEntity
                        .status(204)
                        .body(accounts);
            }
        } else {
            return ResponseEntity
                    .status(403)
                    .body(accounts);
        }
    }

    public boolean isUserEmployee(){
        try{
            User user = userService.getUserByUserId(userService.getUserId());
            if (user.getType().equals(User.Type.EMPLOYEE)) return true;
            else return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public String generateRandomIban() {
        Random random = new Random();
        String iban = "NL";
        int int_random = random.nextInt(10);
        iban += Integer.toString(int_random);
        int_random = random.nextInt(10);
        iban += Integer.toString(int_random);
        iban += "INHO0";
        for (int i = 0; i < 10; i++) {
            int n = random.nextInt(10);
            iban += Integer.toString(n);
        }
        List<Account> accounts = getAllAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            String ibanToCheck = account.getIban();
            if (iban == ibanToCheck) {
                generateRandomIban();
            }
        }
        return iban;
    }



}
