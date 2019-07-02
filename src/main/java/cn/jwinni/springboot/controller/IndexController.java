package cn.jwinni.springboot.controller;

import cn.jwinni.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/login")
    public String getLogin(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("getuser")
    public List getUser(){

        return userService.getAllUser();
    }
}
