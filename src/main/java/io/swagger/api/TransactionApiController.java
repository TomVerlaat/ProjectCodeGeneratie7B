package io.swagger.api;

import java.math.BigDecimal;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T07:37:10.859Z[GMT]")
@Controller
public class TransactionApiController implements TransactionApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deposit(@ApiParam(value = "") @RequestParam(value="account", required=false)  String account
,@ApiParam(value = "") @RequestParam(value="amount", required=false)  BigDecimal amount
,@ApiParam(value = "") @RequestParam(value="userPerformingId", required=false)  Integer userPerformingId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> transaction(@ApiParam(value = "") @RequestParam(value="timestamp", required=false)  String timestamp
,@ApiParam(value = "") @RequestParam(value="accountFrom", required=false)  String accountFrom
,@ApiParam(value = "") @RequestParam(value="accountTo", required=false)  String accountTo
,@ApiParam(value = "") @RequestParam(value="amount", required=false)  BigDecimal amount
,@ApiParam(value = "") @RequestParam(value="userPerformingId", required=false)  Integer userPerformingId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> withdraw(@ApiParam(value = "") @RequestParam(value="account", required=false)  String account
,@ApiParam(value = "") @RequestParam(value="amount", required=false)  BigDecimal amount
,@ApiParam(value = "") @RequestParam(value="userPerformingId", required=false)  Integer userPerformingId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
