package com.chatop.chatop.service;

import com.chatop.chatop.entity.UserDB;
import com.chatop.chatop.model.UserModel;
import com.chatop.chatop.model.response.MeResponse;
import com.chatop.chatop.repository.UserRepository;
import com.chatop.chatop.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Iterable<UserDB> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void delUsers(){
        userRepository.deleteAll();
    }
    @Override
    //Ajoute un UserDB dans la base
    public void createUser(UserModel userModel){
            UserDB userDB = new UserDB();
            userDB.setName(userModel.getName());
            userDB.setPassword(userModel.getPassword());
            userDB.setEmail(userModel.getEmail());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //On encode le password
            userDB.setPassword(passwordEncoder.encode(userDB.getPassword()));
            userRepository.save(userDB);
    }
    @Override
    public UserDB findById(Long id){
        Iterable<UserDB> users = getUsers();
        for (UserDB user : users){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
    @Override
    public UserDB findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    @Override
    // Permet de tester si le mot de passe existe dans la base
    public boolean isUserValid(String password, UserDB user) {
        return passwordEncoder.matches(password, user.getPassword());
    }
    @Override
    public MeResponse createMeResponse(UserDB userDB){
        MeResponse meResponse = new MeResponse();
        meResponse.setId(userDB.getId());
        meResponse.setName(userDB.getName());
        meResponse.setEmail(userDB.getEmail());
        meResponse.setCreated_at(userDB.getCreated_at());
        meResponse.setUpdated_at(userDB.getUpdated_at());
        return meResponse;
    }

}
