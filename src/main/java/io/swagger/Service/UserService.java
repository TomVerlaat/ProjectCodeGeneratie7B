package io.swagger.Service;

import io.swagger.dao.UserRepository;
import io.swagger.model.NewUserBody;
import io.swagger.model.UpdateBody;
import io.swagger.model.UpdateLoggedInUserBody;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.threeten.bp.LocalDate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getUserByUserId(Long id) {
        try {
            return userRepository.getUserById(id);
        } catch (Exception e) {
        }
        return new User();
    }

    @PutMapping
    public void deactivateUser(Long id) {
        User userToDeactivate = userRepository.getUserById(id);
        userToDeactivate.setActive(false);
        userRepository.save(userToDeactivate);
    }

    @PutMapping
    public User updateUser(User newUser) {
        userRepository.save(newUser);
        return newUser;
    }

    public long getUserIDByUsername(String username) {
        try {
            User tempUser = userRepository.getUserByUsername(username);
            return tempUser.getId();
        } catch (Exception e) {
            return 0;
        }
    }

    public ResponseEntity deactivateUserResponseEntity(long id) {
        User userToDeactivate = getUserByUserId(id);
        if (userToDeactivate == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            deactivateUser(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    public ResponseEntity getAllUsersResponseEntity() {
        List<User> users = getAllUsers();
        return ResponseEntity
                .status(200)
                .body(users);
    }

    public ResponseEntity getUserResponseEntity(long id) {
        User user = getUserByUserId(id);

        try {
            user.getType();
        } catch (Exception e) {

        }
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(user);
        } else {
            return ResponseEntity
                    .status(200)
                    .body(user);
        }
    }

    public ResponseEntity newUserResponseEntity(NewUserBody body) {
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

        addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getId());
    }

    public ResponseEntity updateUserResponseEntity(Long id, UpdateBody body) {
        User checkUser = getUserByUserId(id);
        if (checkUser == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            // Fill updated user with filled in parameters
            checkUser.setUsername(body.getUsername());
            if(body.getPassword().equals(body.getConfirmPassword())){
                checkUser.setPassword(new BCryptPasswordEncoder().encode(body.getPassword()));
            }else{
                checkUser.setPassword(checkUser.getPassword());
            }
            checkUser.setFirstName(body.getFirstName());
            checkUser.setLastName(body.getLastName());
            checkUser.setEmail(body.getEmail());
            checkUser.setBirthdate(body.getBirthdate());
            checkUser.setAddress(body.getAddress());
            checkUser.setPostalcode(body.getPostalcode());
            checkUser.setCity(body.getCity());
            checkUser.setPhoneNumber(body.getPhoneNumber());
            checkUser.setActive(true);
            checkUser.setType(body.getType());
            checkUser.setTransactionLimit(body.getTransactionLimit());
            checkUser.setMaximumDebt(body.getMaximumDebt());
            checkUser.setMaxTransactions(body.getMaxTransactions());

            // Save updated user
            updateUser(checkUser);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    public long getUserId() {
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            return getUserIDByUsername(loggedInUser.getName());
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public ResponseEntity getLoggedInUserResponseEntity() {
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            Long userid = getUserIDByUsername(loggedInUser.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(getUserByUserId(userid));
        }
        catch (Exception e)
        {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity updateLoggedInUserResponseEntity(Long id, UpdateLoggedInUserBody body) {
        if(getUserId() == id){
            User checkUser = getUserByUserId(id);
            if (checkUser == null) {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } else {
                // Fill updated user with filled in parameters
                if(body.getPassword().equals(body.getConfirmPassword())){
                    checkUser.setPassword(new BCryptPasswordEncoder().encode(body.getPassword()));
                }else{
                    checkUser.setPassword(checkUser.getPassword());
                }
                checkUser.setFirstName(body.getFirstName());
                checkUser.setLastName(body.getLastName());
                checkUser.setEmail(body.getEmail());
                checkUser.setBirthdate(body.getBirthdate());
                checkUser.setAddress(body.getAddress());
                checkUser.setPostalcode(body.getPostalcode());
                checkUser.setCity(body.getCity());
                checkUser.setPhoneNumber(body.getPhoneNumber());
                checkUser.setActive(true);

                // Save updated user
                updateUser(checkUser);
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }
    }
}