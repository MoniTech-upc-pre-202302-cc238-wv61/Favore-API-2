package com.monitech.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monitech.restapi.model.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {

}