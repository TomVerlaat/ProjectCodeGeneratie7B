package io.swagger.Service;

import io.swagger.dao.UserRepository;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

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

    public void addUser(User user)
    {
        userRepository.save(user);
    }

    public User getUserByUserId(Long id) { return userRepository.getUserById(id); }

    @PutMapping
    public void deactivateUser(Long id)
    {
        User userToDeactivate = userRepository.getUserById(id);
        userToDeactivate.setActive(false);
        userRepository.save(userToDeactivate);
    }

    @PutMapping
    public User updateUser(User newUser){
        userRepository.save(newUser);
        return newUser;
    }

    public long getUserIDByUsername(String username) {
        try {
            User tempUser = userRepository.getUserByUsername(username);
            return tempUser.getId();
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
