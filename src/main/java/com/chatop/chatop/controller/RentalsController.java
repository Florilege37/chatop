package com.chatop.chatop.controller;

import com.chatop.chatop.entity.RentalsDB;
import com.chatop.chatop.model.RentalsModel;
import com.chatop.chatop.model.response.MessageResponse;
import com.chatop.chatop.model.response.RentalListResponse;
import com.chatop.chatop.service.RentalsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@Tag(name = "Rentals", description = "Rentals Controller")
@Api("API pour les opérations CRUD sur les locations.")
@RestController
@RequestMapping("/api/")
public class RentalsController {

    @Autowired
    private RentalsServiceImpl rentalsService;

    @Operation(
            summary = "Get Rentals/id",
            description = "Permet de récupérer une offre de location en fonction de son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = RentalsController.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", content = {}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(), mediaType = "application/json") })})
    @GetMapping("rentals/{id}")
    public ResponseEntity<?> getRentalsById(@PathVariable("id") Long id){
        RentalsDB rental = rentalsService.findById(id);
        if (rental == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(rentalsService.createRentalResponse(rental));
    }
    @Operation(
            summary = "Get All Rentals",
            description = "Permet de récupérer toutes les offres de location")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = RentalListResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", content = {})})
    @GetMapping("rentals")
    public ResponseEntity<?> getRentals(){
        RentalListResponse rentalListResponse = new RentalListResponse();
        Iterable<RentalsDB> rental = rentalsService.getRentals();
        for (RentalsDB rentalDB : rental){
            rentalListResponse.getRentals().add(rentalsService.createRentalResponse(rentalDB));
        }
        return ResponseEntity.ok(rentalListResponse);
    }

    @Operation(
            summary = "Post Rentals",
            description = "Permet de publier une offre de location")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MessageResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", content = {})})
    @PostMapping("rentals")
    public ResponseEntity<?> postRentals(HttpServletRequest request, @ModelAttribute RentalsModel rentalsModel, Principal user) throws IOException {
        rentalsService.createRental(request,rentalsModel, user);
        return ResponseEntity.ok(new MessageResponse("Rental created !"));
    }

    @Operation(
            summary = "Put Rentals",
            description = "Permet de modifier une offre de location en fonction de son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MessageResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", content = {}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(), mediaType = "application/json") })})
    @PutMapping("rentals/{id}")
    public ResponseEntity<?> putRentals(HttpServletRequest request, @PathVariable("id") Long id, @ModelAttribute RentalsModel rentalsModel, Principal user) throws IOException {
        RentalsDB rental = rentalsService.findById(id);
        if (rental == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rentalsService.updateRental(id, rentalsModel);
        return ResponseEntity.ok(new MessageResponse("Rental updated !"));
    }
}
