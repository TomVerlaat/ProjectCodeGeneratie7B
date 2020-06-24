package io.swagger.Service;

import io.swagger.dao.TransactionRepository;
import io.swagger.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.threeten.bp.OffsetDateTime;
import java.util.List;

@Service
public class TransactionService {

@Autowired
private TransactionRepository transactionRepository;

@Autowired
private UserService userService;

@Autowired
private AccountService accountService;

public TransactionService() {
}

    public int getTransactionsToday(String iban) {
    return transactionRepository.getTransactionsToday(iban,OffsetDateTime.now().withHour(0));
    };

    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    public List<Transaction> getTransactionByUserId(double userId) {
        return (List<Transaction>) transactionRepository.getAllByUserPerformingId(userId);
    }

    public Transaction getTransactionById(Long id) {
        try {
            return transactionRepository.getById(id);
        }
        catch (Exception e)
        {
            return new Transaction();
        }
    }

    public List<Transaction> GetTransactionsFromIban(String iban) {
        return transactionRepository.getByAccountFromOrAccountToOrderByTimestampDesc(iban,iban);
    }

    public void addTransaction(Transaction transaction)
    {
        transactionRepository.save(transaction);
    }

    public ResponseEntity depositTransactionResponseEntity(DepositBody body){
        // Only User with account has access
        boolean access = accountAccess(body.getAccountTo());

        if(access && underDayLimit(body.getAccountTo()) && validAmount(body.getAmount())) {
            Transaction transaction = new Transaction();
            transaction.setAccountFrom("NL01INHO0000000001");
            transaction.setAccountTo(body.getAccountTo());
            transaction.setAmount(body.getAmount());
            transaction.setUserPerformingId(userService.getUserId());
            transaction.setTransactionType(Transaction.TransactionTypeEnum.DEPOSIT);
            addTransaction(transaction);

            // Deposit money into account
            Account account = accountService.getAccountByIban(body.getAccountTo());
            account.setBalance(account.getBalance() + body.getAmount());
            accountService.updateAccount(account);

            return ResponseEntity.status(HttpStatus.CREATED).body(transaction.getId());
        }
        else
        {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity withdrawTransactionResponseEntity(WithdrawBody body){
        // Only User with account has access
        boolean access = accountAccess(body.getAccountFrom());

        if(access && underDayLimit(body.getAccountFrom()) && validAmount(body.getAmount())) {
            // Withdraw money from account
            Account account = accountService.getAccountByIban(body.getAccountFrom());
            if (enoughFunds(account.getBalance(),body.getAmount())) {

                Transaction transaction = new Transaction();
                transaction.setAccountFrom(body.getAccountFrom());
                transaction.setAccountTo("NL01INHO0000000001");
                transaction.setAmount(body.getAmount());
                transaction.setUserPerformingId(userService.getUserId());
                transaction.setTransactionType(Transaction.TransactionTypeEnum.WITHDRAWAL);
                addTransaction(transaction);

                account.setBalance(account.getBalance() - body.getAmount());
                accountService.updateAccount(account);

                return ResponseEntity.status(HttpStatus.CREATED).body(transaction.getId());
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient funds");
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    public ResponseEntity payTransactionResponseEntity(PaymentBody body){
        // Only user with account has access.
        boolean access = accountAccess(body.getAccountFrom());

        boolean ibanExists = false;
        if(ibanExists(body.getAccountFrom()) == true && ibanExists(body.getAccountTo()) == true)
        {
            ibanExists = true;
        }

        // Check prerequirements
        if(ibanExists && access && underDayLimit(body.getAccountFrom()) && validAmount(body.getAmount())) {

            // Check account balance and status
            Account accountFrom = accountService.getAccountByIban(body.getAccountFrom());
            if (accountFrom.getType() == Account.TypeEnum.CURRENT) {
                if (enoughFunds(accountFrom.getBalance(), body.getAmount())) {

                    Transaction transaction = new Transaction();
                    transaction.setAccountFrom(body.getAccountFrom());
                    transaction.setAccountTo(body.getAccountTo());
                    transaction.setAmount(body.getAmount());
                    transaction.setUserPerformingId(userService.getUserId());
                    transaction.setDescription(body.getDescription());

                    // Retrieve money
                    accountFrom.setBalance(accountFrom.getBalance() - body.getAmount());
                    accountService.updateAccount(accountFrom);

                    // Add money
                    Account accountTo = accountService.getAccountByIban(body.getAccountTo());
                    accountTo.setBalance(accountTo.getBalance() + body.getAmount());
                    accountService.updateAccount(accountTo);

                    transaction.setTransactionType(Transaction.TransactionTypeEnum.PAYMENT);
                    addTransaction(transaction);
                    return ResponseEntity.status(HttpStatus.CREATED).body(transaction.getId());
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient funds");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Drawing money from savings account is prohibited");
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    public ResponseEntity transferTransactionResponseEntity(TransferBody body) {
        if (userService.getUserId() != 0) {
            // Check if user has acces to AccountFrom
            boolean accessToAccountFrom = accountAccess(body.getAccountFrom());
            // Check if user hass access to AccountTo
            boolean accessToAccountTo = accountAccess(body.getAccountTo());

            // Check if account has enough funds
            Account accountFrom = accountService.getAccountByIban(body.getAccountFrom());

            // Execute transaction
            if (accessToAccountFrom & accessToAccountTo && underDayLimit(body.getAccountFrom()) && enoughFunds(accountFrom.getBalance(), body.getAmount()) && validAmount(body.getAmount())) {
                // Widthdraw funds
                accountFrom.setBalance(accountFrom.getBalance() - body.getAmount());
                accountService.updateAccount(accountFrom);

                // Add funds
                Account accountTo = accountService.getAccountByIban(body.getAccountTo());
                accountTo.setBalance(accountTo.getBalance() + body.getAmount());
                accountService.updateAccount(accountTo);

                // Save transaction
                Transaction transaction = new Transaction();
                transaction.setAccountFrom(body.getAccountFrom());
                transaction.setAccountTo(body.getAccountTo());
                transaction.setAmount(body.getAmount());
                transaction.setUserPerformingId(userService.getUserId());
                transaction.setDescription(body.getDescription());

                transaction.setTransactionType(Transaction.TransactionTypeEnum.TRANSFER);
                addTransaction(transaction);
                return ResponseEntity.status(HttpStatus.CREATED).body(transaction.getId());
            }
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    public ResponseEntity getAllTransactionsResponseEntity() {
        // Only for employees
        if(userService.getUserId() != 0) {
            User user = userService.getUserByUserId(userService.getUserId());
            if (user.getType().equals(User.Type.EMPLOYEE)) {
                List<Transaction> transactions = getAllTransactions();
                return ResponseEntity
                        .status(200)
                        .body(transactions);
            }
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    public ResponseEntity getTransactionByIdResponseEntity(long id) {
        //Only creator of transaction and employee have access

        //Check if user has access to transaction
        if(userService.getUserId() != 0) {
            Transaction transaction = getTransactionById(id);
            if(transaction!= null) {
                boolean access = false;
                if (transaction.getUserPerformingId().equals(userService.getUserId())) {
                    access = true;
                }

                // Check if logged in user is employee
                User user = userService.getUserByUserId(userService.getUserId());
                if (user.getType().equals(User.Type.EMPLOYEE)) {
                    access = true;
                }


                if (access) {
                    return ResponseEntity
                            .status(200)
                            .body(transaction);
                }
            }
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    public ResponseEntity getTransactionsByIbanResponseEntity(String iban) {
        // Owner of account and employee have access
        if(userService.getUserId() != 0) {
            List<Account> userAccounts = accountService.getAccountsByUserId(userService.getUserId());
            boolean access = false;

            // Owner of IBAN account
            for (Account account : userAccounts) {
                if (account.getIban().equals(iban)) {
                    access = true;
                    break;
                }
            }
            //Employee
            User user = userService.getUserByUserId(userService.getUserId());
            if (user.getType().equals(User.Type.EMPLOYEE)) {
                access = true;
            }

            if (access) {
                List<Transaction> transactions = GetTransactionsFromIban(iban);
                return ResponseEntity
                        .status(200)
                        .body(transactions);
            }
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    private boolean ibanExists(String Iban) {
        Account account  = accountService.getAccountByIban(Iban);
        if(account == null)
        {
            return false;
        }
        return true;
    }

    private boolean accountAccess(String accountToCheck) {
        User user = userService.getUserByUserId(userService.getUserId());
        List<Account> userAccounts = accountService.getAccountsByUserId(userService.getUserId());
        for (Account account:userAccounts) {
            if(account.getIban().equals(accountToCheck) || user.getType().equals(User.Type.EMPLOYEE))
            {
                return true;
            }
        }
        return false;
    }

    private boolean validAmount(double amount) {
        User user = userService.getUserByUserId(userService.getUserId());
        double maxAmount = user.getTransactionLimit();
        if(amount > 0 && amount <= maxAmount)
        {
            return true;
        }
        return false;
    }

    private boolean enoughFunds(double accountBalance ,double transactionAmount) {
        User user = userService.getUserByUserId(userService.getUserId());
        double maximumDebt = user.getMaximumDebt();
        if(accountBalance - transactionAmount >= maximumDebt)
        {
            return true;
        }
        return false;
    }

    private boolean underDayLimit(String iban)
    {
        int transactions = getTransactionsToday(iban);
        Account account = accountService.getAccountByIban(iban);
        if(account != null) {
            if (transactions <= account.getMaxTransactions()) {
                return true;
            }
        }
         return false;
    }
}
