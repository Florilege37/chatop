package com.chatop.chatop.service.interfaces;

import com.chatop.chatop.entity.UserDB;
import com.chatop.chatop.model.UserModel;
import com.chatop.chatop.model.response.MeResponse;

import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService {
    public Iterable<UserDB> getUsers();

    public void delUsers();

    //Ajoute un UserDB dans la base
    void createUser(UserModel userModel) throws SQLIntegrityConstraintViolationException;

    UserDB findById(Long id);

    UserDB findByEmail(String email);

    // Permet de tester si le mot de passe existe dans la base
    boolean isUserValid(String password, UserDB user);

    MeResponse createMeResponse(UserDB userDB);
}
