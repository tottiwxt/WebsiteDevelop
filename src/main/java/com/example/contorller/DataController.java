package com.example.contorller;

import com.example.model.User;
import com.example.model.ViewObject;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DataController {
    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public ModelAndView readUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("getUsers");
        List<User> users = userService.getAllUsers();
        List<ViewObject> vos = new ArrayList<ViewObject>();

        int i = 0;
        for(User user: users){
            ViewObject vo = new ViewObject();
            vo.set("user",user);
            vos.add(vo);

        }


        modelAndView.addObject("vos",vos);
        return  modelAndView;
     }
}
