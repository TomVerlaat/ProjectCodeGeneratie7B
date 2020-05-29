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

    public ResponseEntity <Void> deactivateUser(@ApiParam(value = "userId to deactivate",required=true) @PathVariable("userid") Long id)
    {
        User userToDeactivate = userService.getUserByUserId(id);
        if (userToDeactivate == null){
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        else {
            userService.deactivateUser(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    public ResponseEntity getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity
                .status(200)
                .body(users);
    }

    public ResponseEntity<User> getUser(@ApiParam(value = "user to retrieve",required=true) @PathVariable("userid") Long id
) {
        User user = userService.getUserByUserId(id);
        if (user == null){
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(user);
        }
        else{
            return ResponseEntity
                    .status(200)
                    .body(user);
        }
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

    public ResponseEntity <Void> updateUser(@ApiParam(value = ""  )  @Valid @RequestBody User user
) {
        User checkUser = userService.getUserByUserId(user.getId());
        if (checkUser == null){
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        else{
            User newUser = new User();
            // Fill updated user with filled in parameters
            newUser.setId(user.getId());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setBirthdate(LocalDate.now());
            newUser.setAddress(user.getAddress());
            newUser.setPostalcode(user.getPostalcode());
            newUser.setCity(user.getCity());
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setActive(true);
            newUser.setType(user.getType());

            // Save updated user
            userService.updateUser(newUser);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

}
