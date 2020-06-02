package io.swagger.dao;

import io.swagger.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> getById(Long value);

    User getUserById(Long value);

    User findByUsername(String value);

    User getUserByUsername(String value);
}

