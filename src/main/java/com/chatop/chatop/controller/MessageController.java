package com.chatop.chatop.controller;

import com.chatop.chatop.model.MessageModel;
import com.chatop.chatop.model.response.MessageResponse;
import com.chatop.chatop.service.MessageServiceImpl;
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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Message", description = "Message Controller")
@Api("API pour les opérations CRUD sur les messages.")
@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @Operation(
            summary = "Post message",
            description = "Permet de créer un nouveau message")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MessageResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", content = {}) })
    @PostMapping("/messages")
    public ResponseEntity<?> addMessages(@Valid @RequestBody MessageModel messageModel, Errors errors) {
        // Si le RequestBody contient des erreurs
        if (errors.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        messageService.createMessage(messageModel);
        return ResponseEntity.ok(new MessageResponse("Message send with success"));
    }
}
