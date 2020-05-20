package io.swagger.api;

//import io.swagger.Service.TransactionService;
import io.swagger.Service.TransactionService;
import io.swagger.model.Body1;
import io.swagger.model.Body2;
import io.swagger.model.Body3;
import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
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

    public ResponseEntity<Void> depositTransaction(@ApiParam(value = ""  )  @Valid @RequestBody Body2 body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> newTransaction(@ApiParam(value = ""  )  @Valid @RequestBody Body1 body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity getAllTransactions() {
                    List<Transaction> transactions = transactionService.getAllTransactions();
                    return ResponseEntity
                            .status(200)
                            .body(transactions);
    }


    /*
    public ResponseEntity<List<Transaction>> getTransactionDetails(@ApiParam(value = "Enter Transaction ID",required=true) @PathVariable("id") Integer transactionid
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Transaction>>(objectMapper.readValue("[ {\n  \"transactionType\" : \"Deposit\",\n  \"accountTo\" : \"NL01INHO0000000000\",\n  \"amount\" : 100,\n  \"userPerformingId\" : 10000000001,\n  \"description\" : \"Money for your new RB-17\",\n  \"id\" : 10000000001,\n  \"accountFrom\" : \"NL01INHO0000000000\",\n  \"timestamp\" : \"2020-05-07T12:32:28Z\"\n}, {\n  \"transactionType\" : \"Deposit\",\n  \"accountTo\" : \"NL01INHO0000000000\",\n  \"amount\" : 100,\n  \"userPerformingId\" : 10000000001,\n  \"description\" : \"Money for your new RB-17\",\n  \"id\" : 10000000001,\n  \"accountFrom\" : \"NL01INHO0000000000\",\n  \"timestamp\" : \"2020-05-07T12:32:28Z\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }
     */



    public ResponseEntity<List<Transaction>> getTransactionDetails(@ApiParam(value = "Enter Transaction ID",required=true) @PathVariable("id") Integer id
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
                List<Transaction> transactions = transactionService.getTransactionDetails((long)id);
                return ResponseEntity
                        .status(200)
                        .body(transactions);
        }
        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<Void> witdhrawTransaction(@ApiParam(value = ""  )  @Valid @RequestBody Body3 body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
