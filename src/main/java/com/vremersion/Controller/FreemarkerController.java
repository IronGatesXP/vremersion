package com.vremersion.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FreemarkerController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = {"/forgot"}, method = RequestMethod.GET)
    public String forget(){
        return "forgot";
    }

    @RequestMapping(value = {"/reset"}, method = RequestMethod.GET)
    public String reset(){
        return "reset";
    }

    @RequestMapping(value = {"/navbar"}, method = RequestMethod.GET)
    public String navbar(){
        return "navbar";
    }

    @RequestMapping(value = {"/footer"}, method = RequestMethod.GET)
    public String footer(){
        return "foooter";
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = {"/my_course"}, method = RequestMethod.GET)
    public String my_course(){
        return "my_course";
    }

    @RequestMapping(value = {"/user_information"}, method = RequestMethod.GET)
    public String user_information(){
        return "user_information";
    }
}
