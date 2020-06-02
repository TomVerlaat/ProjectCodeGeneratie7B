package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.Service.AccountService;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Account;
import io.swagger.model.NewAccountBody;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

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

    public ResponseEntity addAccount(@Valid @RequestBody NewAccountBody body)
    {
        Account account = new Account();
        account.setBalance(0);
        account.setActive(true);
        account.setCurrency(body.getCurrency());
        account.setIban(body.getIban());
        account.setType(body.getType());

        account.setUserId(body.getUserId());
        if (accountService.addAccount(account)){
            return ResponseEntity.status(HttpStatus.CREATED).body(account.getId());
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(account.getIban());
        }
    }

    public ResponseEntity <Void> deactivateAccount(@ApiParam(value = "IBAN to deactivate",required=true) @PathVariable("iban") String iban)
    {
        Account accountToDeactivate = accountService.getAccountByIban(iban);
        if (accountToDeactivate == null){
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        else {
            accountService.deactivateAccount(iban);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    public ResponseEntity <Account> getAccountByIBAN(@ApiParam(value = "Account IBAN",required=true) @PathVariable("iban") String iban)
    {
        Account account = accountService.getAccountByIban(iban);
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

    public ResponseEntity <List<Account>> getAccountByUserID(@ApiParam(value = "UserId to find account",required=true) @PathVariable("userId") long userId)
    {
        System.out.println("Current user: " + getUserId());
        List<Account> accounts = accountService.getAccountsByUserId((long)2);

        if (accounts.size() > 0) {
            return ResponseEntity
                    .status(200)
                    .body(accounts);
        }
        else{
            return ResponseEntity
                    .status(204)
                    .body(accounts);
        }
    }


    public ResponseEntity getAllAccounts(@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Long limit
            ,@ApiParam(value = "filter for LastName") @Valid @RequestParam(value = "lastName", required = false) String lastName){
        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.size() > 0) {
            return ResponseEntity
                    .status(200)
                    .body(accounts);
        }
        else{
            return ResponseEntity
                    .status(204)
                    .body(accounts);
        }
    }

    private String getUserId() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return loggedInUser.getName();
    }
}
