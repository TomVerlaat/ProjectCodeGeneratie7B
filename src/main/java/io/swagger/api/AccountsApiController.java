package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.Service.AccountService;
import io.swagger.Service.UserService;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Account;
import io.swagger.model.NewAccountBody;
import io.swagger.model.User;
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
import java.util.Random;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T18:16:38.158Z[GMT]")
@Controller
public class AccountsApiController implements AccountsApi {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

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
        account.setIban(generateRandomIban());
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
        if (isUserAuthorized()) {
            Account accountToDeactivate = accountService.getAccountByIban(iban);
            if (accountToDeactivate == null) {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } else {
                accountService.deactivateAccount(iban);
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
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

    public ResponseEntity <List<Account>> getAccountByUserID(/*@ApiParam(value = "UserId to find account",required=true) @PathVariable("userId") long userId*/)
    {
        List<Account> accounts;

            accounts = accountService.getAccountsByUserId(getUserId());
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


    public ResponseEntity getAllAccounts(@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Long limit
            ,@ApiParam(value = "filter for LastName") @Valid @RequestParam(value = "lastName", required = false) String lastName){
        List<Account> accounts;
        if (isUserAuthorized()) {
            accounts = accountService.getAllAccounts();
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
        else{
            accounts = null;
            return ResponseEntity
                    .status(403)
                    .body(accounts);
        }
    }

    private long getUserId() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserIDByUsername(loggedInUser.getName());
    }

    public boolean isUserAuthorized(){
        try{
            User user = userService.getUserByUserId(getUserId());
            if (user.getType() == User.Type.EMPLOYEE) return true;
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
        List<Account> accounts = accountService.getAllAccounts();
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
