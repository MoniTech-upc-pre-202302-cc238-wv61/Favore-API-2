package com.monitech.restapi.service;

import java.util.List;

import com.monitech.restapi.model.Offer;

public interface OfferService {

    Offer createOffer(Offer offer);

    Offer updateOffer(Long offer_id, Offer offer);

    Offer getOfferById(Long offer_id);

    Offer deleteOffer(Long offer_id);

    List<Offer> getAllOffers();
}