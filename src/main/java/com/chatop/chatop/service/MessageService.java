package com.chatop.chatop.service;

import com.chatop.chatop.entity.MessageDB;
import com.chatop.chatop.model.MessageModel;
import com.chatop.chatop.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Iterable<MessageDB> getMessages() {
        return messageRepository.findAll();
    }
    public void createMessage(MessageModel messageModel){
        MessageDB messageDB = new MessageDB();
        messageDB.setUser_id(messageModel.getUser_id());
        messageDB.setRental_id(messageModel.getRental_id());
        messageDB.setMessage(messageModel.getMessage());
        messageRepository.save(messageDB);
    }
}
