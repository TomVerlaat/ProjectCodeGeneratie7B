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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity <Void> deactivateUser(@ApiParam(value = "userId to deactivate",required=true) @PathVariable("userid") Long id) {
        return userService.deactivateUserResponseEntity(id);
    }

    public ResponseEntity getAllUsers() {
        return userService.getAllUsersResponseEntity();
    }

    public ResponseEntity<User> getUser(@ApiParam(value = "user to retrieve",required=true) @PathVariable("userid") Long id) {
        return userService.getUserResponseEntity(id);
    }

    public ResponseEntity newUser(@Valid @RequestBody NewUserBody body) {
        return userService.newUserResponseEntity(body);
    }

    public ResponseEntity <Void> updateUser(@ApiParam(value = ""  )  @Valid @RequestBody User user) {
        return userService.updateUserResponseEntity(user);
    }
}
