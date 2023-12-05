package com.chatop.chatop.repository;

import com.chatop.chatop.entity.UserDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDB, Long> {
    UserDB findByName(String name);
}
