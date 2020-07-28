package com.lambdaschool.songsuggest.repository;

import com.lambdaschool.songsuggest.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository
        extends CrudRepository<User, Long> {

    User findByUsername(String username);


    List<User> findByUsernameContainingIgnoreCase(String name);
}
