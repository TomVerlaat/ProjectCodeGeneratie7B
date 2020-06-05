package io.swagger.api;

//import io.swagger.Service.TransactionService;
import io.swagger.Service.AccountService;
import io.swagger.Service.TransactionService;
import io.swagger.Service.UserService;
import io.swagger.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T18:16:38.158Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    private long getUserId() {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            return userService.getUserIDByUsername(loggedInUser.getName());
    }

    // WERKT!
    public ResponseEntity depositTransaction(@Valid @RequestBody DepositBody body
    ) {
        // Only User with account has access
        List<Account> userAccounts = accountService.getAccountsByUserId(getUserId());
        boolean access = false;
        for (Account account:userAccounts) {
            if(account.getIban().equals(body.getAccountTo()))
            {
                access = true;
                break;
            }
        }

        if(access && body.getAmount() > 0) {
            Transaction transaction = new Transaction();
            transaction.setAccountFrom("NL01INHO0000000001");
            transaction.setAccountTo(body.getAccountTo());
            transaction.setAmount(body.getAmount());
            transaction.setTransactionType(Transaction.TransactionTypeEnum.DEPOSIT);
            transactionService.addTransaction(transaction);

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

    // WERKT!
    public ResponseEntity WithdrawTransaction(@Valid @RequestBody WithdrawBody body
    ) {
        // Only User with account has access
        List<Account> userAccounts = accountService.getAccountsByUserId(getUserId());
        boolean access = false;
        for (Account account:userAccounts) {
            if(account.getIban().equals(body.getAccountFrom()))
            {
                access = true;
                break;
            }
        }

        if(access && body.getAmount() > 0) {
            // Withdraw money from account
            Account account = accountService.getAccountByIban(body.getAccountFrom());
            if (account.getBalance() >= body.getAmount()) {

                Transaction transaction = new Transaction();
                transaction.setAccountFrom(body.getAccountFrom());
                transaction.setAccountTo("NL01INHO0000000001");
                transaction.setAmount(body.getAmount());
                transaction.setTransactionType(Transaction.TransactionTypeEnum.WITHDRAWAL);
                transactionService.addTransaction(transaction);

                account.setBalance(account.getBalance() - body.getAmount());
                accountService.updateAccount(account);

                return ResponseEntity.status(HttpStatus.CREATED).body(transaction.getId());
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient funds");
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    //WERKT!
    public ResponseEntity payTransaction(@Valid @RequestBody PaymentBody body)
    {
        // Only user with account has access.
        List<Account> userAccounts = accountService.getAccountsByUserId(getUserId());
        boolean access = false;
        for (Account account:userAccounts) {
            if(account.getIban().equals(body.getAccountFrom()))
            {
                access = true;
                break;
            }
        }

        if(access && body.getAmount() > 0) {
            Account accountFrom = accountService.getAccountByIban(body.getAccountFrom());
            if (accountFrom.getBalance() >= body.getAmount()) {

                Transaction transaction = new Transaction();
                transaction.setAccountFrom(body.getAccountFrom());
                transaction.setAccountTo(body.getAccountTo());
                transaction.setAmount(body.getAmount());
                transaction.setDescription(body.getDescription());

                // Retrieve money
                accountFrom.setBalance(accountFrom.getBalance() - body.getAmount());
                accountService.updateAccount(accountFrom);

                // Add money
                Account accountTo = accountService.getAccountByIban(body.getAccountTo());
                accountTo.setBalance(accountTo.getBalance() + body.getAmount());
                accountService.updateAccount(accountTo);

                transaction.setTransactionType(Transaction.TransactionTypeEnum.PAYMENT);
                transactionService.addTransaction(transaction);
                return ResponseEntity.status(HttpStatus.CREATED).body(transaction.getId());
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient funds");
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    // WERKT!
    public ResponseEntity TransferTransaction(@Valid @RequestBody TransferBody body)
    {
        // Check if user has acces to AccountFrom
        List<Account> userAccounts = accountService.getAccountsByUserId(getUserId());
        boolean accessToAccountFrom = false;
        for (Account account:userAccounts) {
            if(account.getIban().equals(body.getAccountFrom()))
            {
                accessToAccountFrom = true;
                break;
            }
        }

        // Check if user hass access to AccountTo
        boolean accessToAccountTo= false;
        for (Account account:userAccounts) {
            if(account.getIban().equals(body.getAccountFrom()))
            {
                accessToAccountTo = true;
                break;
            }
        }

        // Check if account has enough funds
        Account accountFrom = accountService.getAccountByIban(body.getAccountFrom());
        boolean enoughFunds = false;
        if (accountFrom.getBalance() >= body.getAmount()) {
            enoughFunds = true;
        }

        // Execute transaction
        if (accessToAccountFrom & accessToAccountTo && enoughFunds && body.getAmount() > 0) {
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
            transaction.setDescription(body.getDescription());

            transaction.setTransactionType(Transaction.TransactionTypeEnum.TRANSFER);
            transactionService.addTransaction(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction.getId());
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }


    // WERKT!
    public ResponseEntity getAllTransactions() {
        // Only for employees
        User user = userService.getUserByUserId(getUserId());
        if(user.getType().equals(User.Type.EMPLOYEE))
        {
            List<Transaction> transactions = transactionService.getAllTransactions();
            return ResponseEntity
                    .status(200)
                    .body(transactions);
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    // Nog beveiligen
    public ResponseEntity<Transaction> getTransactionById(@ApiParam(value = "Transaction ID",required=true) @PathVariable("id") Long id)
    {
        //Only creator of transaction and employee have access

        //Check if user has access to transaction
        Transaction transaction = transactionService.getTransactionById(id);
        boolean access = false;
        if(transaction.getUserPerformingId().equals(getUserId()))
        {
            access = true;
        }

        // Check if logged in user is employee
        User user = userService.getUserByUserId(getUserId());
        if(user.getType().equals(User.Type.EMPLOYEE))
        {
            access = true;
        }


        if(access) {
            return ResponseEntity
                    .status(200)
                    .body(transaction);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    // WERKT!
    public ResponseEntity <List<Transaction>> getTransactionsByIBAN(@ApiParam(value = "Account IBAN",required=true) @PathVariable("iban") String iban)
    {
        // Owner of account and employee have access
        List<Account> userAccounts = accountService.getAccountsByUserId(getUserId());
        boolean access = false;

        // Owner of IBAN account
        for (Account account:userAccounts) {
            if(account.getIban().equals(iban))
            {
                access = true;
                break;
            }
        }
        //Employee
        User user = userService.getUserByUserId(getUserId());
        if(user.getType().equals(User.Type.EMPLOYEE))
        {
            access = true;
        }

        if(access) {
            List<Transaction> transactions = transactionService.GetTransactionsFromIban(iban);
            return ResponseEntity
                    .status(200)
                    .body(transactions);
        }
        else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

}
