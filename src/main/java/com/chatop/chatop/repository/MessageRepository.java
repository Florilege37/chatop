package com.chatop.chatop.repository;

import com.chatop.chatop.entity.MessageDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageDB, Long> {

}
