package com.sprint.hcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.hcs.entities.User;
import com.sprint.hcs.exception.ForBiddenException;
import com.sprint.hcs.exception.UserNotFoundException;
import com.sprint.hcs.loginmodule.LoginService;
@RestController
public class LoginController {

    @Autowired
    LoginService logServ;


    @PostMapping("/Login")
    public User loginUser(@RequestBody User user) throws UserNotFoundException {
        return logServ.loginWithData(user.getUsername(), user.getPassword());
    }


    @PostMapping("/Logout")
    public User logOut(@RequestBody User user) throws ForBiddenException, UserNotFoundException {
        return logServ.logoutPresentUser(user.getUsername());
    }

}
