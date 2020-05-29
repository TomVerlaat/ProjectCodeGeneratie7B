package io.swagger.Service;

import io.swagger.dao.TransactionRepository;
import io.swagger.dao.UserRepository;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
