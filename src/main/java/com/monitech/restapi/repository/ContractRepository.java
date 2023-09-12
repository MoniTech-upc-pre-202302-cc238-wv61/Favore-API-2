package com.monitech.restapi.repository;

import com.monitech.restapi.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

}
