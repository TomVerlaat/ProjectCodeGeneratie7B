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

    @Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity addAccount(@Valid @RequestBody NewAccountBody body) {
        return accountService.addAccountResponseEntity(body);
    }

    public ResponseEntity <Void> deactivateAccount(@ApiParam(value = "IBAN to deactivate",required=true) @PathVariable("iban") String iban) {
        return accountService.deactivateAccountResponseEntity(iban);
    }

    public ResponseEntity <Account> getAccountByIBAN(@ApiParam(value = "Account IBAN",required=true) @PathVariable("iban") String iban) {
        return accountService.getAccountByIbanResponseEntity(iban);
    }

    public ResponseEntity <List<Account>> getByCurrentUser() {
        return accountService.getByCurrentUserResponseEntity();
    }

    public ResponseEntity <List<Account>> getByUserId(@ApiParam(value = "UserId to find account",required=true) @PathVariable("userId") long userId){
        return accountService.getByUserIdResponseEntity(userId);
    }

    public ResponseEntity getAllAccounts() {
        return accountService.getAllAccountsResponseEntity();
    }
}
