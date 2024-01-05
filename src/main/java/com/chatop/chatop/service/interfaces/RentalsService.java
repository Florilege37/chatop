package com.chatop.chatop.service.interfaces;

import com.chatop.chatop.model.RentalsModel;
import com.chatop.chatop.model.RentalsModelLienFile;
import com.chatop.chatop.model.response.RentalResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.security.Principal;

public interface RentalsService {

    public Iterable<RentalsModelLienFile> getRentals();

    void createRental(HttpServletRequest request, RentalsModel rentalsModel, Principal user) throws IOException;

    RentalsModelLienFile findById(Long id);

    RentalResponse createRentalResponse(RentalsModelLienFile rentalsModel);

    public RentalResponse createAllRentalResponse(RentalsModelLienFile rentalsModel);

    void updateRental(Long id, RentalsModelLienFile rentalsModel) throws IOException;
}
