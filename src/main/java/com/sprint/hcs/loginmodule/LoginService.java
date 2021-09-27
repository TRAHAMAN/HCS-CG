package com.sprint.hcs.loginmodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.hcs.dao.UserRepository;
import com.sprint.hcs.dao.ManualQueries.QueryClassPersisitContext;
import com.sprint.hcs.entities.User;
import com.sprint.hcs.exception.UserNotFoundException;

@Service
public class LoginService {
    @Autowired
    private QueryClassPersisitContext qcp;

    @Autowired
    private UserRepository uRepo;


    public User loginWithData(String username,String password) throws UserNotFoundException {
        User user;
        user = qcp.findByUserName(username);
        if(user.isLoggedIn())throw new UserNotFoundException("User Already Logged In ");
        if(!user.getPassword().equals(password))throw new UserNotFoundException("Invalid Password");
        user.setLoggedIn(true);
        uRepo.saveAndFlush(user);
        return user;
    }



    public User logoutPresentUser(String userName) throws UserNotFoundException {
        User user = qcp.findByUserName(userName);

        user.setLoggedIn(false);


        return uRepo.saveAndFlush(user);
    }




}















