package com.chatop.chatop.service;

import com.chatop.chatop.entity.RentalsDB;
import com.chatop.chatop.model.RentalsModel;
import com.chatop.chatop.model.response.RentalResponse;
import com.chatop.chatop.repository.RentalsRepository;
import com.chatop.chatop.service.interfaces.RentalsService;
import com.chatop.chatop.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;

@Service
public class RentalsServiceImpl implements RentalsService {

    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;
    @Override
    public Iterable<RentalsDB> getRentals() {
        return rentalsRepository.findAll();
    }

    @Override
    public void createRental(HttpServletRequest request, RentalsModel rentalsModel, Principal user) throws IOException {
        RentalsDB rentalsDB = new RentalsDB();
        rentalsDB.setName(rentalsModel.getName());
        rentalsDB.setSurface(rentalsModel.getSurface());
        rentalsDB.setPrice(rentalsModel.getPrice());

        //On crée un URI pour la photo
        StringBuilder sb = new StringBuilder();
        sb.append("data:image/png;base64,");
        sb.append(StringUtils.newStringUtf8(Base64.getEncoder().encode(rentalsModel.getPicture().getBytes())));


        rentalsDB.setDescription(rentalsModel.getDescription());
        //On récupère l'ID du user connecté
        String mail = user.getName();
        System.out.println(mail);
        long owner_id = userService.findByEmail(mail).getId();
        rentalsDB.setOwnerId(owner_id);

        //On récupère l'image
        MultipartFile file = rentalsModel.getPicture();
        String fileName = file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath("/images/");
        //Crée un fichier dans le serveur
        File dest = new File(filePath + fileName);
        if(!dest.exists()) {
            new File(filePath).mkdir();
        }
        //On transfère l'image dans le serveur
        file.transferTo(dest);

        String contextPath = request.getContextPath();
        String relativePath = "/images/" + fileName;
        //On génère l'URL
        String imageUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + relativePath;
        rentalsDB.setPicture(imageUrl);

        rentalsRepository.save(rentalsDB);
    }

    @Override
    public Optional<RentalsDB> findById(Long id){
        return rentalsRepository.findById(id);
    }

    @Override
    public RentalResponse createRentalResponse(Optional<RentalsDB> rentalsDB){
        RentalResponse rentalResponse = new RentalResponse();
        if (rentalsDB.isPresent()){
            rentalResponse.setId(rentalsDB.get().getId());
            rentalResponse.setName(rentalsDB.get().getName());
            rentalResponse.setSurface(rentalsDB.get().getSurface());
            rentalResponse.setPrice(rentalsDB.get().getPrice());
            rentalResponse.setPicture(rentalsDB.get().getPicture());
            rentalResponse.setDescription(rentalsDB.get().getDescription());
            rentalResponse.setOwner_id(rentalsDB.get().getOwnerId());
            rentalResponse.setCreated_at(rentalsDB.get().getCreatedAt());
            rentalResponse.setUpdated_at(rentalsDB.get().getUpdatedAt());
        }

        return rentalResponse;
    }

    @Override
    public RentalResponse createAllRentalResponse(RentalsDB rentalsDB){
        RentalResponse rentalResponse = new RentalResponse();
            rentalResponse.setId(rentalsDB.getId());
            rentalResponse.setName(rentalsDB.getName());
            rentalResponse.setSurface(rentalsDB.getSurface());
            rentalResponse.setPrice(rentalsDB.getPrice());
            rentalResponse.setPicture(rentalsDB.getPicture());
            rentalResponse.setDescription(rentalsDB.getDescription());
            rentalResponse.setOwner_id(rentalsDB.getOwnerId());
            rentalResponse.setCreated_at(rentalsDB.getCreatedAt());
            rentalResponse.setUpdated_at(rentalsDB.getUpdatedAt());

        return rentalResponse;
    }

    @Override
    public void updateRental(Long id, RentalsModel rentalsModel) throws IOException {
        Optional<RentalsDB> rentalsDB = rentalsRepository.findById(id);
        if (rentalsDB.isPresent()){
            RentalsDB rentals = rentalsDB.get();

            rentals.setName(rentalsModel.getName());
            rentals.setSurface(rentalsModel.getSurface());
            rentals.setPrice(rentalsModel.getPrice());
            rentals.setDescription(rentalsModel.getDescription());
            rentals.setUpdatedAt(LocalDate.now());

            rentalsRepository.save(rentals);
        }
    }

    @Override
    public void delRentals(){
        rentalsRepository.deleteAll();
    }
}
