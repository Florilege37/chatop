package com.chatop.chatop.service.interfaces;

import com.chatop.chatop.entity.MessageDB;
import com.chatop.chatop.model.MessageModel;

public interface MessageService {

    Iterable<MessageDB> getMessages();
    void delMessages();
    void createMessage(MessageModel messageModel);
}
