package com.monitech.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.monitech.restapi.exception.ValidationException;
import com.monitech.restapi.model.Offer;
import com.monitech.restapi.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/api/favore/v1")
public class OfferController {

    private final OfferService offerService;

    private void validateOffer(Offer offer) {
        if (offer.getBudgetAmount() == null || offer.getBudgetAmount() <= 0) {
            throw new ValidationException("Budget amount is required and should be greater than 0");
        }
        if (offer.getOfferDate() == null) {
            throw new ValidationException("Offer date is required");
        }
        if (offer.getUser() == null) {
            throw new ValidationException("User reference is required");
        }
        if (offer.getContract() == null) {
            throw new ValidationException("Contract reference is required");
        }
    }

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    //URL: http://localhost:8080/api/favore/v1/offers
    //Method: POST
    @Transactional
    @PostMapping("/offers")
    public ResponseEntity<Offer> createOffer(Offer offer) {
        validateOffer(offer);
        Offer createdOffer = offerService.createOffer(offer);
        return ResponseEntity.ok(createdOffer);
    }

    //URL: http://localhost:8080/api/favore/v1/offers
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = offerService.getAllOffers();
        return ResponseEntity.ok(offers);
    }

    //URL: http://localhost:8080/api/favore/v1/offers/{offer_id}
    //Method: GET
    @Transactional
    @GetMapping("/offers/{offer_id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable(value = "offer_id") Long offer_id) {
        Offer offer = offerService.getOfferById(offer_id);
        return ResponseEntity.ok(offer);
    }

    //URL: http://localhost:8080/api/favore/v1/offers/{offer_id}
    //Method: PUT
    @Transactional
    @PutMapping("/offers/{offer_id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable(value = "offer_id") Long offer_id, Offer offer) {
        validateOffer(offer);
        Offer updatedOffer = offerService.updateOffer(offer_id, offer);
        return ResponseEntity.ok(updatedOffer);
    }

    //URL: http://localhost:8080/api/favore/v1/offers/{offer_id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/offers/{offer_id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable(value = "offer_id") Long offer_id) {
        Offer deletedOffer = offerService.deleteOffer(offer_id);
        return ResponseEntity.ok(deletedOffer);
    }
}
