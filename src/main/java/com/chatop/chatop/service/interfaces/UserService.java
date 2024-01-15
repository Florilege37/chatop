package com.chatop.chatop.service.interfaces;

import com.chatop.chatop.entity.UserDB;
import com.chatop.chatop.model.UserModel;
import com.chatop.chatop.model.response.MeResponse;

import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService {

    //Ajoute un UserDB dans la base
    void createUser(UserModel userModel) throws SQLIntegrityConstraintViolationException;

    UserModel findById(Long id);

    // Trouve un user grace à son email, puis le retourne.
    UserModel findByEmail(String email);

    // Permet de tester si le mot de passe existe dans la base
    boolean isUserValid(String password, UserModel user);

    MeResponse createMeResponse(UserModel userModel);
}
