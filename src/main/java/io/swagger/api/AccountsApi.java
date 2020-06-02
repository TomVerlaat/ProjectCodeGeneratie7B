/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.annotations.*;
import io.swagger.model.NewAccountBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T18:16:38.158Z[GMT]")
@Api(value = "Accounts", description = "the Accounts API")
public interface AccountsApi {

    @ApiOperation(value = "Create new Account", nickname = "addAccount", notes = "Adds an item to the system", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "item created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 409, message = "an existing item already exists") })
    @RequestMapping(value = "/Accounts/new",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addAccount(@ApiParam(value = ""  )  @Valid @RequestBody NewAccountBody account
);


    @ApiOperation(value = "Deactivate Account", nickname = "deactivateAccount", notes = "Deactivates an Account", tags={ "Accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Succesfull"),
        @ApiResponse(code = 400, message = "bad request", response = String.class),
        @ApiResponse(code = 401, message = "API key is missing or invalid"),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = String.class) })
    @RequestMapping(value = "/Accounts/deactivate/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity deactivateAccount(@ApiParam(value = "IBAN to deactivate",required=true) @PathVariable("iban") String iban
);


    @ApiOperation(value = "Get Account by IBAN", nickname = "getAccountByIBAN", notes = "Get Account that matches IBAN", response = Account.class, responseContainer = "List", tags={ "Accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Account", response = Account.class, responseContainer = "List"),
            @ApiResponse(code = 204, message = "No account with this Iban"),
        @ApiResponse(code = 400, message = "bad request", response = String.class),
        @ApiResponse(code = 401, message = "API key is missing or invalid"),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = String.class) })
    @RequestMapping(value = "/Accounts/GetByIban/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getAccountByIBAN(@ApiParam(value = "IBAN to get account",required=true) @PathVariable("iban") String iban
);


    @ApiOperation(value = "Get all Accounts that a user has by the UserID", nickname = "getAccountByUserID", notes = "Get all Accounts that a user has by the UserID", response = Account.class, responseContainer = "List", tags={ "Accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Account", response = Account.class, responseContainer = "List"),
            @ApiResponse(code = 204, message = "No accounts for this user"),
        @ApiResponse(code = 400, message = "bad request", response = String.class),
        @ApiResponse(code = 401, message = "API key is missing or invalid"),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = String.class) })
    @RequestMapping(value = "/Accounts/GetByUserId/{userId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity getAccountByUserID(@ApiParam(value = "UserId to find account",required=true) @PathVariable("userId") long userId);


    @ApiOperation(value = "Get All Accounts", nickname = "getAllAccounts", notes = "Get all accounts in the system", response = Account.class, responseContainer = "List", tags={ "Accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Accounts", response = Account.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad request", response = String.class),
        @ApiResponse(code = 401, message = "API key is missing or invalid"),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = String.class) })
    @RequestMapping(value = "/Accounts",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Account>> getAllAccounts(@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Long limit
,@ApiParam(value = "filter for LastName") @Valid @RequestParam(value = "lastName", required = false) String lastName
);

}
