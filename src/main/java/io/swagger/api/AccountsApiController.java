package io.swagger.api;

import io.swagger.Service.AccountService;
import io.swagger.model.Account;
import io.swagger.model.Body;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.NewAccount;
import io.swagger.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T18:16:38.158Z[GMT]")
@Controller
public class AccountsApiController implements AccountsApi {

    @Autowired
    private AccountService accountService;

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity addAccount(@Valid @RequestBody NewAccount body
) {
        Account newAccount = new Account();
        newAccount.setBalance(0);
        newAccount.setCurrency(Account.CurrencyEnum.EUR);
        newAccount.setIban(body.getIban());

        //Convert enum of NewAccount to Account
        Account.TypeEnum typeOfNewAccount = newAccount.getType();
        newAccount.setType(typeOfNewAccount);

        newAccount.setUserId(body.getUserId());
        if (accountService.addAccount(newAccount)){
            return ResponseEntity.status(HttpStatus.CREATED).body(newAccount.getId());
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(newAccount.getIban());
        }
    }

    public ResponseEntity <Void> deactivateAccount(@ApiParam(value = "IBAN to deactivate",required=true) @PathVariable("iban") String iban
) {
        /*
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
        */
        accountService.deactivateAccount(iban);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity <Account> getAccountByIBAN(@ApiParam(value = "Account IBAN",required=true) @PathVariable("iban") String iban)
    {
        Account accounts = accountService.getAccountByIban(iban);
        return ResponseEntity
                .status(200)
                .body(accounts);
    }

    public ResponseEntity <List<Account>> getAccountByUserID(@ApiParam(value = "UserID to get accounts",required=true) @PathVariable("userid") Long userid)
    {
        List<Account> accounts = accountService.getAccountsByUserId(userid);
        return ResponseEntity
                .status(200)
                .body(accounts);
    }


    public ResponseEntity getAllAccounts(@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Long limit
            ,@ApiParam(value = "filter for LastName") @Valid @RequestParam(value = "lastName", required = false) String lastName){
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity
                .status(200)
                .body(accounts);
    }

}
