package com.chatop.chatop.service.interfaces;

import com.chatop.chatop.entity.RentalsDB;
import com.chatop.chatop.model.RentalsModel;
import com.chatop.chatop.model.response.RentalResponse;

public interface RentalsService {

    public Iterable<RentalsDB> getRentals();

    void delRentals();

    void createRental(RentalsModel rentalsModel);

    RentalsDB findById(Long id);

    RentalResponse createRentalResponse(RentalsDB rentalsDB);

    void updateRental(Long id, RentalsModel rentalsModel);
}
