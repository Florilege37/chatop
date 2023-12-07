package com.chatop.chatop.controller;

import com.chatop.chatop.model.MessageModel;
import com.chatop.chatop.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/messages")
    public String addMessages(MessageModel messageModel) {
        messageService.createMessage(messageModel);
        return "Message send with success";
    }
}
