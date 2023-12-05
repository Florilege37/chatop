package com.chatop.chatop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/")
    public String getResource(){
        return "A value ...";
    }

}
