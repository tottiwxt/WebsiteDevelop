package com.example.service;

import com.example.dao.UserDAO;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    public String say(){
        return "I,bonbon,show me your money";
    }
    public User getUser(int id){
        return userDAO.selectById(id);
    }
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }
}
