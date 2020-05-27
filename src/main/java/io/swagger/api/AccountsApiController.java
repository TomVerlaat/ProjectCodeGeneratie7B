package io.swagger.api;

import io.swagger.Service.AccountService;
import io.swagger.model.Account;
import io.swagger.model.Body;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
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

    public ResponseEntity<Void> addAccount(@ApiParam(value = ""  )  @Valid @RequestBody Body body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deactivateAccount(@ApiParam(value = "IBAN to deactivate",required=true) @PathVariable("iban") String iban
) {
        /*
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
        */
        accountService.deactivateAccount(iban);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /*
    public ResponseEntity getAccountByIBAN(@ApiParam(value = "IBAN to get account",required=true) @PathVariable("iban") String iban
) {
        Account account = accountService.getAccountByIban("NL01INHO00000000001");
        return ResponseEntity
                .status(200)
                .body(account);
    }

     */

    public ResponseEntity <List<Account>> getAccountByIBAN(@ApiParam(value = "Account IBAN",required=true) @PathVariable("iban") String iban)
    {
        List <Account> accounts = accountService.getAccountByIban(iban);
        return ResponseEntity
                .status(200)
                .body(accounts);
    }



    public ResponseEntity<List<Account>> getAccountByUserID(@ApiParam(value = "UserID to get accounts",required=true) @PathVariable("userid") Long userid
) {
       /* String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Account>>(objectMapper.readValue("[ {\n  \"balance\" : {\n    \"accountId\" : 10000000001,\n    \"balance\" : 99.95,\n    \"id\" : 10000000001\n  },\n  \"iban\" : \"NLxxINHO0xxxxxxxxx\",\n  \"active\" : true,\n  \"currency\" : \"EUR\",\n  \"id\" : 10000000001,\n  \"type\" : \"Savings\",\n  \"userId\" : 10000000002\n}, {\n  \"balance\" : {\n    \"accountId\" : 10000000001,\n    \"balance\" : 99.95,\n    \"id\" : 10000000001\n  },\n  \"iban\" : \"NLxxINHO0xxxxxxxxx\",\n  \"active\" : true,\n  \"currency\" : \"EUR\",\n  \"id\" : 10000000001,\n  \"type\" : \"Savings\",\n  \"userId\" : 10000000002\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED); */
        List<Account> accounts = accountService.getAccountsById(userid);
        return ResponseEntity
                .status(200)
                .body(accounts);
    }

    /*
    public ResponseEntity<List<Account>> getAllAccounts(@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Integer limit
,@ApiParam(value = "filter for LastName") @Valid @RequestParam(value = "lastName", required = false) String lastName
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Account>>(objectMapper.readValue("[ {\n  \"balance\" : {\n    \"accountId\" : 10000000001,\n    \"balance\" : 99.95,\n    \"id\" : 10000000001\n  },\n  \"iban\" : \"NLxxINHO0xxxxxxxxx\",\n  \"active\" : true,\n  \"currency\" : \"EUR\",\n  \"id\" : 10000000001,\n  \"type\" : \"Savings\",\n  \"userId\" : 10000000002\n}, {\n  \"balance\" : {\n    \"accountId\" : 10000000001,\n    \"balance\" : 99.95,\n    \"id\" : 10000000001\n  },\n  \"iban\" : \"NLxxINHO0xxxxxxxxx\",\n  \"active\" : true,\n  \"currency\" : \"EUR\",\n  \"id\" : 10000000001,\n  \"type\" : \"Savings\",\n  \"userId\" : 10000000002\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }*/

    public ResponseEntity getAllAccounts(@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Long limit
            ,@ApiParam(value = "filter for LastName") @Valid @RequestParam(value = "lastName", required = false) String lastName){
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity
                .status(200)
                .body(accounts);
    }

}
