package com.chatop.chatop.controller;

import com.chatop.chatop.model.UserModel;
import com.chatop.chatop.model.response.Token;
import com.chatop.chatop.service.JWTService;
import com.chatop.chatop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    private JWTService jwtService;

    public AuthController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<Token> getToken(@RequestBody UserModel userModel) {
        return ResponseEntity.ok(new Token(jwtService.generateToken(userModel)));
    }

    @PostMapping("/register")
    public ResponseEntity<Token> registerUser(@RequestBody UserModel userModel) {
        userService.createUser(userModel);
        return ResponseEntity.ok(new Token(jwtService.generateToken(userModel)));
    }
}
