package com.chandu.NoBroker.controller;

import com.chandu.NoBroker.model.User;
import com.chandu.NoBroker.service.UserService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/userId")
    public String getUser(@PathVariable Long userId) {
        User user = userService.findUserById(userId);

//        System.out.println(user.getProperties());
        return "success";
    }
}
