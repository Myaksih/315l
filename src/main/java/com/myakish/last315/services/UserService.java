package com.myakish.last315.services;



import com.myakish.last315.models.User;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    User findUserById(Long id);

    void update(User user);

    void saveUser(User user);

    boolean deleteUserById(Long id);

}