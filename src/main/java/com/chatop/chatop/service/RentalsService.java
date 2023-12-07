package com.chatop.chatop.service;

import com.chatop.chatop.entity.RentalsDB;
import com.chatop.chatop.model.RentalsModel;
import com.chatop.chatop.repository.RentalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalsService {

    @Autowired
    private RentalsRepository rentalsRepository;

    public Iterable<RentalsDB> getRentals() {
        return rentalsRepository.findAll();
    }

    public void createRental(RentalsModel rentalsModel){
        RentalsDB rentalsDB = new RentalsDB();
        rentalsDB.setName(rentalsModel.getName());
        rentalsDB.setSurface(rentalsModel.getSurface());
        rentalsDB.setPrice(rentalsModel.getPrice());
        rentalsDB.setPicture(rentalsModel.getPicture());
        rentalsDB.setDescription(rentalsModel.getDescription());
        rentalsDB.setOwner_id(rentalsModel.getOwner_id());
        rentalsRepository.save(rentalsDB);
    }

    public RentalsDB findById(Long id){
        Iterable<RentalsDB> rentals = getRentals();
        for (RentalsDB rental : rentals){
            if (rental.getId().equals(id)){
                return rental;
            }
        }
        return null;
    }
}
