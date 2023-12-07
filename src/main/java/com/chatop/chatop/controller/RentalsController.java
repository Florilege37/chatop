package com.chatop.chatop.controller;

import com.chatop.chatop.entity.RentalsDB;
import com.chatop.chatop.service.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalsController {

    @Autowired
    private RentalsService rentalsService;

    @GetMapping("/api/rentals/{id}")
    public String getRentalsById(@PathVariable("id") Long id){
        RentalsDB rental = rentalsService.findById(id);
        if(rental != null){
            String resultat = "id : " + rental.getId() +
                    " \nnom : " + rental.getName() +
                    " \nsurface : " + rental.getSurface() +
                    " \nprice " + rental.getPrice() +
                    " \npicture " + rental.getPicture() +
                    " \ndescription : " + rental.getDescription() +
                    " \nowner id : " + rental.getOwner_id() +
                    " \ncreated at " + rental.getCreated_at() +
                    " \nupdated at " + rental.getUpdated_at();

            return resultat;
        }
        return null;
    }

    @GetMapping("/api/rentals")
    public String getRentals(){
        Iterable<RentalsDB> rental = rentalsService.getRentals();
        if(rental != null){
            return rental.toString();
        }
        return null;
    }
}
