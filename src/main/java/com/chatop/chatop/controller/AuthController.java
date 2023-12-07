package com.chatop.chatop.controller;

import com.chatop.chatop.configuration.SpringSecurityConfig;
import com.chatop.chatop.entity.UserDB;
import com.chatop.chatop.model.UserModel;
import com.chatop.chatop.model.response.Message;
import com.chatop.chatop.model.response.Token;
import com.chatop.chatop.service.JWTService;
import com.chatop.chatop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getToken(@RequestBody UserModel userModel) {
        UserDB user = userService.findByEmail(userModel.getEmail());
        if (user == null){
            return new ResponseEntity<>(new Message("Email ou mot de passe incorrect"), HttpStatus.UNAUTHORIZED);
        } else if (userService.isUserValid(userModel.getPassword(), user)) {
            return ResponseEntity.ok(new Token(jwtService.generateToken(userModel)));
        } else {
            return new ResponseEntity<>(new Message("Email ou mot de passe incorrect"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Token> registerUser(@RequestBody UserModel userModel) {
        if (userService.findByEmail(userModel.getEmail()) != null || userModel.getName() == null || userModel.getName().isEmpty()
        || userModel.getEmail() == null || userModel.getEmail().isEmpty() || userModel.getPassword() == null || userModel.getPassword().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            userService.createUser(userModel);
            return ResponseEntity.ok(new Token(jwtService.generateToken(userModel)));
        }

    }
}
