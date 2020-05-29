package io.swagger.api;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.threeten.bp.LocalDate;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T18:16:38.158Z[GMT]")
@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deactivateUser(@ApiParam(value = ""  )  @Valid @RequestBody Body6 body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity
                .status(200)
                .body(users);
    }

    public ResponseEntity<List<User>> getUser(@ApiParam(value = "user to retrieve",required=true) @PathVariable("userid") Integer userid
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"lastName\" : \"Verstappen\",\n  \"birthdate\" : \"0010-11-16T00:00:00.000+0000\",\n  \"address\" : \"Alphenlaat 42\",\n  \"city\" : \"Zandvoort\",\n  \"active\" : true,\n  \"type\" : \"Customer\",\n  \"firstName\" : \"Max\",\n  \"password\" : \"Password123\",\n  \"phoneNumber\" : \"069876543210\",\n  \"postalcode\" : \"2041 KP\",\n  \"id\" : 10000000001,\n  \"email\" : \"verstappen@jumbo.nl\",\n  \"username\" : \"MaxVerstappen\"\n}, {\n  \"lastName\" : \"Verstappen\",\n  \"birthdate\" : \"0010-11-16T00:00:00.000+0000\",\n  \"address\" : \"Alphenlaat 42\",\n  \"city\" : \"Zandvoort\",\n  \"active\" : true,\n  \"type\" : \"Customer\",\n  \"firstName\" : \"Max\",\n  \"password\" : \"Password123\",\n  \"phoneNumber\" : \"069876543210\",\n  \"postalcode\" : \"2041 KP\",\n  \"id\" : 10000000001,\n  \"email\" : \"verstappen@jumbo.nl\",\n  \"username\" : \"MaxVerstappen\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity newUser(@Valid @RequestBody NewUserBody body
) {
        User user = new User();
        user.setUsername(body.getUsername());
        user.setPassword(body.getPassword());
        user.setFirstName(body.getFirstName());
        user.setLastName(body.getLastName());
        user.setEmail(body.getEmail());
        user.setBirthdate(LocalDate.now());
        user.setAddress(body.getAddress());
        user.setPostalcode(body.getPostalcode());
        user.setCity(body.getCity());
        user.setPhoneNumber(body.getPhoneNumber());
        user.setActive(true);
        user.setType(body.getType());

        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getId());
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = ""  )  @Valid @RequestBody Body5 body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
