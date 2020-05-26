/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

//import io.swagger.Service.TransactionService;
import io.swagger.model.Body2;
import io.swagger.model.Body3;
import io.swagger.model.DepositBody;
import io.swagger.model.Transaction;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T18:16:38.158Z[GMT]")
@Api(value = "Transactions", description = "the Transactions API")
public interface TransactionsApi {

    @ApiOperation(value = "Deposit", nickname = "depositTransaction", notes = "Creates new deposit", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Transactions", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "item created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 409, message = "an existing item already exists") })
    @RequestMapping(value = "/Transactions/deposit",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> depositTransaction(@Valid @RequestBody DepositBody body
);


    /*
    @ApiOperation(value = "Create New Transaction", nickname = "newTransaction", notes = "Creates new transaction", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Transactions", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "item created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 409, message = "an existing item already exists") })
    @RequestMapping(value = "/Transactions/new",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> newTransaction(@ApiParam(value = ""  )  @Valid @RequestBody Body1 body
);
     */
    @ApiOperation(value = "Create New Transaction", nickname = "newTransaction", notes = "Creates new transaction", authorizations = {
            @Authorization(value = "ApiKeyAuth")    }, tags={ "Transactions", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "item created"),
            @ApiResponse(code = 400, message = "invalid input, object invalid"),
            @ApiResponse(code = 409, message = "an existing item already exists") })
    @RequestMapping(value = "/Transactions/new",
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity newTransaction(@RequestBody Transaction transaction
    );


    @ApiOperation(value = "Get all Transactions", nickname = "Get all transactions", notes = "gets transactions", response = Transaction.class, responseContainer = "List", tags={ "Transactions", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transaction", response = Transaction.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad request", response = String.class),
            @ApiResponse(code = 401, message = "API key is missing or invalid"),
            @ApiResponse(code = 404, message = "The specified resource was not found", response = String.class) })
    @RequestMapping(value = "/Transactions",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> getAllTransactions(
    );



    @ApiOperation(value = "Get Transaction details", nickname = "getTransactionById", notes = "Gets particular transaction", response = Transaction.class, responseContainer = "List", tags={ "Transactions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Transaction", response = Transaction.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad request", response = String.class),
        @ApiResponse(code = 401, message = "API key is missing or invalid"),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = String.class) })
    @RequestMapping(value = "/Transactions/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long id
);

    @ApiOperation(value = "Get all transaction from  account Iban", nickname = "getTransactionByIBAN", notes = "Get alll transaction from IBAN", response = Transaction.class, responseContainer = "List", tags={ "Transactions", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transaction", response = Transaction.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad request", response = String.class),
            @ApiResponse(code = 401, message = "API key is missing or invalid"),
            @ApiResponse(code = 404, message = "The specified resource was not found", response = String.class) })
    @RequestMapping(value = "/Transactions/all/{iban}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> getTransactionByIBAN(@PathVariable("iban") String iban
    );


    @ApiOperation(value = "Withdraw", nickname = "witdhrawTransaction", notes = "Creates new transaction", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Transactions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "item created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 409, message = "an existing item already exists") })
    @RequestMapping(value = "/Transactions/withdraw",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> witdhrawTransaction(@ApiParam(value = ""  )  @Valid @RequestBody Body3 body
);

}
