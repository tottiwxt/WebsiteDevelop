package com.example.contorller;



import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    UserService userService;
    @RequestMapping(path = {"/","/hello"})
    @ResponseBody
    public String index(HttpSession session){

        return "Hello World<br>" + session.getAttribute("msg");

    }
    @RequestMapping(value = {"/profile/{name}/{age}"})
    @ResponseBody
    public String profile(@PathVariable("name") String name,
                          @PathVariable("age") int age
                          ){
        return "姓名="+name +" 年龄" +age;

    }
    @RequestMapping("/bonbon")
    public ModelAndView bonbon( HttpServletRequest request,
                                HttpServletResponse response,
                                HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bonbon");
        String hello = "Im bonbon";
        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1,"bonbon1");
        map.put(2,"bonbon2");
        map.put(3,"bonbon3");

        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerNames =request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            sb.append(name + "=" + request.getHeader(name)+"<br>");
        }
        for(Cookie cookie:request.getCookies()){
            sb.append("cookie:"+cookie.getName()+"    =    "+cookie.getValue());
        }

        modelAndView.addObject("hello",  hello);
        modelAndView.addObject("map",map);
        modelAndView.addObject("information",sb);
        return modelAndView;
    }

    @RequestMapping("/welcome")
    ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        modelAndView.addObject("message","Welcome to Spring Boot & Freemarker");
        return modelAndView;
    }
    @RequestMapping("/request")
    @ResponseBody
    public StringBuilder request(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session){
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerNames =request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            sb.append(name + "=" + request.getHeader(name)+"<br>");
        }
        for(Cookie cookie:request.getCookies()){
            sb.append("cookie:"+cookie.getName()+"    =    "+cookie.getValue());
        }
        return sb;
    }

    @RequestMapping("/response")
    @ResponseBody
    public String response(@CookieValue(value = "username", defaultValue = "admin") String username,
                           @RequestParam(value = "key",defaultValue = "null") String key,
                           @RequestParam(value = "value",defaultValue = " 0") String value,
                           HttpServletResponse response
                           ){
        response.addCookie(new Cookie(key,value));
        response.addHeader(key,value);
        return " response :" + username;
    }

    @RequestMapping("/redirect/{code}")
    public RedirectView redirect(@PathVariable("code") int code,
                                 HttpSession session){
        RedirectView red = new RedirectView("/",true);
        if(code == 301){
            session.setAttribute("msg","Jump from 301.");
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        else if(code == 302){
            session.setAttribute("msg","Jump from 30X.");
            red.setStatusCode(HttpStatus.MOVED_TEMPORARILY);
        }
        return red;
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String checkPassword(@RequestParam(value = "password",required = false) String password){
        if(password.equals("1"))
            return "success";
        else
            throw new IllegalArgumentException("password Error");
    }


    @ExceptionHandler()
    @ResponseBody
    public String Exception(Exception e){
        return "exception = " +e.getMessage();
    }
    @RequestMapping(path={"/IOC","/DI"})
    @ResponseBody
    public String IOC(){
        return userService.say();
    }
}
