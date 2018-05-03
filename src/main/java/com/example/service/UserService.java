package com.example.service;

import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.util.ToutiaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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
    public Map<String, Object> Register(String username, String password){
        Map<String, Object> map = new HashMap<String,Object>();
        if(StringUtils.isEmpty(username)){
            map.put("msgname","用户名不能为空");
            return map;
        }
        if(StringUtils.isEmpty(password)){
            map.put("msgpwd","密码不能为空");
            return map;
        }
        User user = userDAO.selectByName(username);
        if(user != null ){
            map.put("msgname","用户名已被注册");
            return map;
        }

        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png ",new Random().nextInt(1000)));
        user.setPassword(ToutiaoUtil.MD5(password+user.getSalt()));
        userDAO.addUser(user);
        return map;
    }
    public Map<String, Object> login(String username, String password){
        Map<String, Object> map = new HashMap<String,Object>();
        if(StringUtils.isEmpty(username)){
            map.put("msgname","用户名不能为空");
            return map;
        }
        if(StringUtils.isEmpty(password)){
            map.put("msgpwd","密码不能为空");
            return map;
        }
        User user = userDAO.selectByName(username);
        if(user == null ){
            map.put("msgname","用户名不存在");
            return map;
        }

        if(!user.getPassword().equals(ToutiaoUtil.MD5(password+user.getSalt()))){
            map.put("msgpwd","密码错误");
        }
        //给登陆用户下发ticket


        return map;
    }
}
