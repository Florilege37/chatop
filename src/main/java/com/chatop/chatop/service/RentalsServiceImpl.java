package com.chatop.chatop.service;

import com.chatop.chatop.entity.RentalsDB;
import com.chatop.chatop.model.RentalsModel;
import com.chatop.chatop.model.RentalsModelLienFile;
import com.chatop.chatop.model.response.RentalResponse;
import com.chatop.chatop.repository.RentalsRepository;
import com.chatop.chatop.service.interfaces.RentalsService;
import com.chatop.chatop.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalsServiceImpl implements RentalsService {

    final String PICTURE_FOLDER = "/images/";

    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;
    @Override
    public Iterable<RentalsModelLienFile> getRentals() {
        Iterable<RentalsDB> rentalsDB = rentalsRepository.findAll();
        List<RentalsModelLienFile> rentalsModelList = new ArrayList<RentalsModelLienFile>();
        for (RentalsDB rental : rentalsDB){
            rentalsModelList.add(modelMapper.map(rental, RentalsModelLienFile.class));
        }
        return rentalsModelList;
    }

    @Override
    public void createRental(HttpServletRequest request, RentalsModel rentalsModel, Principal user) throws IOException {
        RentalsDB rentalsDB = modelMapper.map(rentalsModel, RentalsDB.class);
        rentalsDB.setCreated_at(LocalDate.now());
        rentalsDB.setUpdated_at(LocalDate.now());

        //On récupère l'ID du user connecté
        String mail = user.getName();
        long owner_id = userService.findByEmail(mail).getId();
        rentalsDB.setOwner_id(owner_id);

        //On sauvegarde le lien de l'image
        rentalsDB.setPicture(saveFile(request, rentalsModel.getPicture()));

        rentalsRepository.save(rentalsDB);
    }

    // Save l'image dans un dossier "images" dans tomcat
    private String saveFile(HttpServletRequest request, MultipartFile file) throws IOException {

        final String fileName = file.getOriginalFilename();
        final String filePath = request.getSession().getServletContext().getRealPath(PICTURE_FOLDER);

        //Crée un fichier dans le serveur
        File dest = new File(filePath + fileName);
        if(!dest.exists()) {
            new File(filePath).mkdir();
        }
        //On transfère l'image dans le serveur
        file.transferTo(dest);

        final String contextPath = request.getContextPath();
        final String relativePath = PICTURE_FOLDER + fileName;

        return getAdressImage(request, file);
    }

    // retourne  l'adresse de l'image (localhost:9000/images/...)
    private String getAdressImage(HttpServletRequest request, MultipartFile file){
        final String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        final String contextPath = request.getContextPath();
        final String relativePath = PICTURE_FOLDER + fileName;

        //On génère et retourne l'URL
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + relativePath;
    }

    @Override
    public RentalsModelLienFile findById(Long id){
        if (rentalsRepository.findById(id).isEmpty()){
            return null;
        }
        return modelMapper.map(rentalsRepository.findById(id), RentalsModelLienFile.class);
    }

    @Override
    public RentalResponse createRentalResponse(RentalsModelLienFile rentalsModel){
        return modelMapper.map(rentalsModel, RentalResponse.class);
    }
    @Override
    public RentalResponse createAllRentalResponse(RentalsModelLienFile rentalsModel){
        return modelMapper.map(rentalsModel, RentalResponse.class);
    }

    @Override
    public void updateRental(Long id, RentalsModelLienFile rentalsModel) throws IOException {
        Optional<RentalsDB> rentalsDB = rentalsRepository.findById(id);
        if (rentalsDB.isPresent()){
            RentalsDB rentals = modelMapper.map(rentalsModel, RentalsDB.class);
            rentals.setId(id);
            rentals.setPicture(rentalsDB.get().getPicture());
            rentals.setOwner_id(rentalsDB.get().getOwner_id());
            rentals.setCreated_at(rentalsDB.get().getCreated_at());
            //On récupère l'ID du user connecté
            rentals.setUpdated_at(LocalDate.now());

            rentalsRepository.save(rentals);
        }
    }
}
