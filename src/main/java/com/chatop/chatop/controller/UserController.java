package com.chatop.chatop.controller;

import com.chatop.chatop.entity.UserDB;
import com.chatop.chatop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") Long id){
        UserDB user = userService.findById(id);
        if(user != null){
            String resultat = "id : " + user.getId() +
                             " \nnom : " + user.getName() +
                             " \nemail : " + user.getEmail() +
                             " \ncreated at " + user.getCreated_at() +
                             " \nupdated at " + user.getUpdated_at();
            return resultat;
        }
        return null;
    }
}
