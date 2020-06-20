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

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity depositTransaction(@Valid @RequestBody DepositBody body) {
        return transactionService.depositTransactionResponseEntity(body);
    }

    public ResponseEntity WithdrawTransaction(@Valid @RequestBody WithdrawBody body) {
       return transactionService.withdrawTransactionResponseEntity(body);
    }

    public ResponseEntity payTransaction(@Valid @RequestBody PaymentBody body) {
        return transactionService.payTransactionResponseEntity(body);
    }

    public ResponseEntity TransferTransaction(@Valid @RequestBody TransferBody body) {
        return transactionService.transferTransactionResponseEntity(body);
    }

    public ResponseEntity getAllTransactions() {
        return transactionService.getAllTransactionsResponseEntity();
    }

    public ResponseEntity<Transaction> getTransactionById(@ApiParam(value = "Transaction ID",required=true) @PathVariable("id") Long id) {
        return transactionService.getTransactionByIdResponseEntity(id);
    }

    public ResponseEntity <List<Transaction>> getTransactionsByIBAN(@ApiParam(value = "Account IBAN",required=true) @PathVariable("iban") String iban) {
        return transactionService.getTransactionsByIbanResponseEntity(iban);
    }
}
