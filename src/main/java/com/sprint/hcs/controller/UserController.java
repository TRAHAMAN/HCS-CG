package com.sprint.hcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.hcs.entities.User;
import com.sprint.hcs.exception.ForBiddenException;
import com.sprint.hcs.exception.UserCreationError;
import com.sprint.hcs.exception.UserNotFoundException;
import com.sprint.hcs.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    LoginController logCon;


    @PostMapping("/validateUser")
    public User validateUser(@RequestBody User user) throws Exception {
        return userService.validateUser(user.getUsername(), user.getPassword());
    }


    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) throws UserCreationError, ForBiddenException {
        return userService.addUser(user);
    }


    @DeleteMapping("/removeuser")
    public User removeUser(@RequestBody User user) throws ForBiddenException, UserNotFoundException {
        return userService.removeUser(user);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) throws ForBiddenException, UserNotFoundException {
        return userService.updateUser(user);

    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }
}

