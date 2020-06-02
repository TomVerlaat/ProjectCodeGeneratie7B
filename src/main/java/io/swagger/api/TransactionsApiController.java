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
        return userService.getUserByUsername(loggedInUser.getName());
    }

    public ResponseEntity depositTransaction(@Valid @RequestBody DepositBody body
    ) {
        List<Account> userAccounts = accountService.getAccountsByUserId(getUserId());
        boolean access = false;
        for (Account account:userAccounts) {
            if(account.getIban().equals(body.getAccountTo()))
            {
                access = true;
                break;
            }
        }

        if(access) {
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

    public ResponseEntity WithdrawTransaction(@Valid @RequestBody WithdrawBody body
    ) {
        List<Account> userAccounts = accountService.getAccountsByUserId(getUserId());
        boolean access = false;
        for (Account account:userAccounts) {
            if(account.getIban().equals(body.getAccountFrom()))
            {
                access = true;
                break;
            }
        }

        if(access) {
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


    public ResponseEntity payTransaction(@Valid @RequestBody PaymentBody body)
    {
        List<Account> userAccounts = accountService.getAccountsByUserId(getUserId());
        boolean access = false;
        for (Account account:userAccounts) {
            if(account.getIban().equals(body.getAccountFrom()))
            {
                access = true;
                break;
            }
        }

        if(access) {
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

    public ResponseEntity TransferTransaction(@Valid @RequestBody Transaction transaction)
    {
        transaction.setTransactionType(Transaction.TransactionTypeEnum.TRANSFER);
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction.getId());
    }


    public ResponseEntity getAllTransactions() {
                    List<Transaction> transactions = transactionService.getAllTransactions();
                    return ResponseEntity
                            .status(200)
                            .body(transactions);
    }


    public ResponseEntity<Transaction> getTransactionById(@ApiParam(value = "Transaction ID",required=true) @PathVariable("id") Long id)
    {
        Transaction transactions = transactionService.getTransactionById(id);
        return ResponseEntity
                .status(200)
                .body(transactions);
    }

    public ResponseEntity <List<Transaction>> getTransactionByIBAN(@ApiParam(value = "Account IBAN",required=true) @PathVariable("iban") String iban)
    {
        List <Transaction> transactions = transactionService.GetTransactionsFromIban(iban);
        return ResponseEntity
                .status(200)
                .body(transactions);
    }

}
