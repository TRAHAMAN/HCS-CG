package com.sprint.hcs.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.hcs.entities.User;
import com.sprint.hcs.exception.DataNotFoundInDataBase;
import com.sprint.hcs.exception.ForBiddenException;
import com.sprint.hcs.exception.UserCreationError;
import com.sprint.hcs.service.IAdminService;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;

    @PostMapping("/registeradmin")
    public	HttpStatus registerAdmin(@RequestBody User user) throws UserCreationError , ForBiddenException{
        adminService.registerAdmin(user.getUsername(), user.getPassword());
        return HttpStatus.CREATED;
    }

    @PutMapping("/updateAdmin")
    public User updateAdmin(@RequestBody User user) throws UserCreationError, DataNotFoundInDataBase, ForBiddenException{
        return adminService.updateAdmin(user);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public User deleteAdmin(@PathVariable int id) throws DataNotFoundInDataBase, ForBiddenException{
        return adminService.deleteAdmin(id);
    }

}

