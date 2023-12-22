package com.chatop.chatop.service.interfaces;

import com.chatop.chatop.entity.RentalsDB;
import com.chatop.chatop.model.RentalsModel;
import com.chatop.chatop.model.response.RentalResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.security.Principal;

public interface RentalsService {

    public Iterable<RentalsDB> getRentals();

    void delRentals();

    void createRental(HttpServletRequest request, RentalsModel rentalsModel, Principal user) throws IOException;

    RentalsDB findById(Long id);

    RentalResponse createRentalResponse(RentalsDB rentalsDB);

    void updateRental(Long id, RentalsModel rentalsModel) throws IOException;
}
