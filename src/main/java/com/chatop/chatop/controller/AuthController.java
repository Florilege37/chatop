package com.chatop.chatop.controller;

import com.chatop.chatop.entity.UserDB;
import com.chatop.chatop.model.UserModel;
import com.chatop.chatop.model.response.EmptyResponse;
import com.chatop.chatop.model.response.MeResponse;
import com.chatop.chatop.model.response.MessageResponse;
import com.chatop.chatop.model.response.TokenResponse;
import com.chatop.chatop.service.JWTService;
import com.chatop.chatop.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "Authentication", description = "Authentication Controller")
@Api("API pour les opérations CRUD sur les utilisateurs.")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;
    private JWTService jwtService;
    public AuthController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Operation(
            summary = "Log l'utilisateur",
            description = "Permet de log l'utilisateur en fonction de ses identifiants, puis génère un toker JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TokenResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", content = {})})
    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody UserModel userModel) {
        UserDB user = userService.findByEmail(userModel.getEmail());
        if (user==null){
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }
        else if (userService.isUserValid(userModel.getPassword(), user)) {
            return ResponseEntity.ok(new TokenResponse(jwtService.generateToken(userModel)));
        } else {
            return new ResponseEntity<>(new MessageResponse("error"), HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(
            summary = "Inscription",
            description = "Permet d'inscrire un nouvel utilisateur, puis génère un toker JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TokenResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", content = {})})
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserModel userModel, Errors errors) {
        // Si le RequestBody contient des erreurs
        if (errors.hasErrors()){
            return new ResponseEntity<>(new EmptyResponse(),HttpStatus.BAD_REQUEST);
        }
        try {
            userService.createUser(userModel);
        } catch (Exception exception){
            return new ResponseEntity<>(new EmptyResponse(),HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new TokenResponse(jwtService.generateToken(userModel)));
    }
    @Operation(
            summary = "Get me",
            description = "Permet de récupérer les données de l'utilisateur connecté grâce à son token JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", content = {})})
    @GetMapping("/me")
    public ResponseEntity<?>  me(@AuthenticationPrincipal OidcUser oidcUserString, Principal user){
        //On récupère le mail du user connecté grâce à son Token
        String userMail = user.getName();
        System.out.println(userMail);
        //On récupère le user dans la base grâce au mail
        UserDB userDB = userService.findByEmail(userMail);
        //On renvoie ses données
        return ResponseEntity.ok(userService.createMeResponse(userDB));
    }
}
