package com.sprint.hcs.service;

import java.util.List;

import com.sprint.hcs.entities.User;
import com.sprint.hcs.exception.UserCreationError;
import com.sprint.hcs.exception.UserNotFoundException;


public interface IUserService {

    User validateUser(String username, String password) throws Exception;
    public User addUser(User user) throws UserCreationError;
    public User removeUser(User user) throws UserNotFoundException;
    User updateUser(User user) throws UserNotFoundException ;
    List<User> getAll();
}

