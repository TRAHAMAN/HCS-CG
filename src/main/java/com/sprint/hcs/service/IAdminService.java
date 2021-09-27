package com.sprint.hcs.service;

import org.springframework.stereotype.Service;

import com.sprint.hcs.entities.User;
import com.sprint.hcs.exception.DataNotFoundInDataBase;
import com.sprint.hcs.exception.UserCreationError;



@Service
public interface IAdminService {

    public void registerAdmin(String username, String password) throws UserCreationError;
    public User updateAdmin(User user) throws UserCreationError, DataNotFoundInDataBase;
    public User deleteAdmin(int id) throws DataNotFoundInDataBase;
}

