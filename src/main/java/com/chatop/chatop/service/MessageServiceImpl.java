package com.chatop.chatop.service;

import com.chatop.chatop.entity.MessageDB;
import com.chatop.chatop.model.MessageModel;
import com.chatop.chatop.repository.MessageRepository;
import com.chatop.chatop.service.interfaces.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void createMessage(MessageModel messageModel){
        messageRepository.save(modelMapper.map(messageModel, MessageDB.class));
    }
}
