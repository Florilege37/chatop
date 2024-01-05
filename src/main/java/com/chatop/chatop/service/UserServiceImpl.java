package com.chatop.chatop.service;

import com.chatop.chatop.entity.UserDB;
import com.chatop.chatop.model.UserModel;
import com.chatop.chatop.model.response.MeResponse;
import com.chatop.chatop.repository.UserRepository;
import com.chatop.chatop.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    //Ajoute un UserDB dans la base
    public void createUser(UserModel userModel){
            UserDB userDB = modelMapper.map(userModel, UserDB.class);

            //On encode le password
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDB.setPassword(passwordEncoder.encode(userDB.getPassword()));

            userDB.setCreated_at(LocalDate.now());
            userDB.setUpdated_at(LocalDate.now());

            userRepository.save(userDB);
    }
    @Override
    public UserModel findById(Long id){
        return modelMapper.map(userRepository.findById(id), UserModel.class);
    }
    @Override
    // Trouve un user grace à son email, puis le retourne.
    public UserModel findByEmail(String email){
        //Si on ne trouve pas de user, on renvoie null
        if (userRepository.findByEmail(email) == null){
            return null;
        }
        return modelMapper.map(userRepository.findByEmail(email), UserModel.class);
    }
    @Override
    // Permet de tester si le mot de passe existe dans la base
    public boolean isUserValid(String password, UserModel user) {
        return passwordEncoder.matches(password, user.getPassword());
    }
    @Override
    public MeResponse createMeResponse(UserModel userModel){
        return modelMapper.map(userModel, com.chatop.chatop.model.response.MeResponse.class);
    }


}
