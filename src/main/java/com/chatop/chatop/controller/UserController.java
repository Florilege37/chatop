package com.chatop.chatop.controller;

import com.chatop.chatop.model.UserModel;
import com.chatop.chatop.model.response.MeResponse;
import com.chatop.chatop.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "User Controller")
@Api("API pour les opérations CRUD sur les utilisateurs.")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Operation(
            security = {@SecurityRequirement(name= "bearerAuth")},
            summary = "Get User/id",
            description = "Permet de récupérer un user en fonction de son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", content = {}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(), mediaType = "application/json") })})
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        UserModel user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userService.createMeResponse(user));
    }
}
