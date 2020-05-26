package io.swagger.api;

//import io.swagger.Service.TransactionService;
import io.swagger.Service.TransactionService;
import io.swagger.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity depositTransaction(@Valid @RequestBody DepositBody body
    ) {
        Transaction transaction = new Transaction();
        transaction.setAccountFrom("NL01INHO0000000001");
        transaction.setAccountTo(body.getAccountTo());
        transaction.setAmount(body.getAmount());
        transaction.setTransactionType(Transaction.TransactionTypeEnum.DEPOSIT);
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction.getId());
    }

    public ResponseEntity WithdrawTransaction(@Valid @RequestBody WithdrawBody body
    ) {
        Transaction transaction = new Transaction();
        transaction.setAccountFrom(body.getAccountFrom());
        transaction.setAccountTo("NL01INHO0000000001");
        transaction.setAmount(body.getAmount());
        transaction.setTransactionType(Transaction.TransactionTypeEnum.WITHDRAWAL);
        transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction.getId());
    }


    public ResponseEntity payTransaction(@Valid @RequestBody Transaction transaction)
    {
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
