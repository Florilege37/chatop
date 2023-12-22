package com.chatop.chatop.service;

import com.chatop.chatop.entity.RentalsDB;
import com.chatop.chatop.model.RentalsModel;
import com.chatop.chatop.model.response.RentalResponse;
import com.chatop.chatop.repository.RentalsRepository;
import com.chatop.chatop.service.interfaces.RentalsService;
import com.chatop.chatop.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createRental(RentalsModel rentalsModel, Principal user) throws IOException {
        RentalsDB rentalsDB = new RentalsDB();
        rentalsDB.setName(rentalsModel.getName());
        rentalsDB.setSurface(rentalsModel.getSurface());
        rentalsDB.setPrice(rentalsModel.getPrice());
        rentalsDB.setPicture(Base64.getEncoder().encodeToString(rentalsModel.getPicture().getBytes()));
        rentalsDB.setDescription(rentalsModel.getDescription());

        String mail = jwtService.getUsernamePasswordLoginInfo(user).toString();
        long owner_id = userService.findByEmail(mail).getId();
        rentalsDB.setOwner_id(owner_id);

        rentalsRepository.save(rentalsDB);
    }

    @Override
    public RentalsDB findById(Long id){
        Iterable<RentalsDB> rentals = getRentals();
        for (RentalsDB rental : rentals){
            if (rental.getId().equals(id)){
                return rental;
            }
        }
        return null;
    }

    @Override
    public RentalResponse createRentalResponse(RentalsDB rentalsDB){
        RentalResponse rentalResponse = new RentalResponse();
        rentalResponse.setId(rentalsDB.getId());
        rentalResponse.setName(rentalsDB.getName());
        rentalResponse.setSurface(rentalsDB.getSurface());
        rentalResponse.setPrice(rentalsDB.getPrice());
        rentalResponse.setPicture(rentalsDB.getPicture());
        rentalResponse.setDescription(rentalsDB.getDescription());
        rentalResponse.setCreated_at(rentalsDB.getCreated_at());
        rentalResponse.setUpdated_at(rentalsDB.getUpdated_at());
        return rentalResponse;
    }

    @Override
    public void updateRental(Long id, RentalsModel rentalsModel, Principal user) throws IOException {
        Optional<RentalsDB> rentalsDB = rentalsRepository.findById(id);
        if (rentalsDB.isPresent()){
            RentalsDB rentals = rentalsDB.get();

            rentals.setName(rentalsModel.getName());
            rentals.setSurface(rentalsModel.getSurface());
            rentals.setPrice(rentalsModel.getPrice());
            rentals.setPicture(Base64.getEncoder().encodeToString(rentalsModel.getPicture().getBytes()));
            rentals.setDescription(rentalsModel.getDescription());
            rentals.setUpdated_at(LocalDate.now());

            String mail = jwtService.getUsernamePasswordLoginInfo(user).toString();
            long owner_id = userService.findByEmail(mail).getId();
            rentals.setOwner_id(owner_id);

            rentalsRepository.save(rentals);
        }
    }

    @Override
    public void delRentals(){
        rentalsRepository.deleteAll();
    }
}
