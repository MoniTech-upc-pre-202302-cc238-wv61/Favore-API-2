package com.monitech.restapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitech.restapi.model.Offer;
import com.monitech.restapi.repository.OfferRepository;
import com.monitech.restapi.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer updateOffer(Long offer_id, Offer offer) {
        Optional<Offer> existingOffer = offerRepository.findById(offer_id);

        if (!existingOffer.isPresent()) {
            throw new RuntimeException("Offer not found with id: " + offer_id);
        }

        offer.setOffer_id(offer_id);
        return offerRepository.save(offer);
    }

    @Override
    public Offer getOfferById(Long offer_id) {
        return offerRepository.findById(offer_id).orElse(null);
    }

    @Override
    public Offer deleteOffer(Long offer_id) {
        Optional<Offer> existingOffer = offerRepository.findById(offer_id);

        if (!existingOffer.isPresent()) {
            throw new RuntimeException("Offer not found with id: " + offer_id);
        }

        offerRepository.deleteById(offer_id);
        return existingOffer.get();
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }
}