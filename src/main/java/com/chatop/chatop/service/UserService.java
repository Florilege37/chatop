package com.chatop.chatop.service;

import com.chatop.chatop.entity.UserDB;
import com.chatop.chatop.model.UserModel;
import com.chatop.chatop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Iterable<UserDB> getUsers() {
        return userRepository.findAll();
    }
    public void delUsers(){
        userRepository.deleteAll();
    }

    //Ajoute un UserDB dans la base
    public void createUser(UserModel userModel){
        if (userModel.getPassword()!=null){
            UserDB userDB = new UserDB();
            userDB.setName(userModel.getName());
            userDB.setPassword(userModel.getPassword());
            userDB.setEmail(userModel.getEmail());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //On encode le password
            userDB.setPassword(passwordEncoder.encode(userDB.getPassword()));
            userRepository.save(userDB);
        }
    }

    public UserDB findById(Long id){
        Iterable<UserDB> users = getUsers();
        for (UserDB user : users){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public UserDB findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    // Permet de tester si le mot de passe existe dans la base
    public boolean isUserValid(String password, UserDB user) {
        return passwordEncoder.matches(password, user.getPassword());
    }

}
