package com.monitech.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.monitech.restapi.exception.ValidationException;
import com.monitech.restapi.model.Contract;
import com.monitech.restapi.service.ContractService;

import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api/favore/v1")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    private void validateContract(Contract contract) {
        if (contract.getDescription() == null || contract.getDescription().isEmpty()) {
            throw new ValidationException("Description is required");
        }
        if (contract.getPublishDate() == null) {
            throw new ValidationException("Publish date is required");
        }
        if (contract.getStatus() == null || contract.getStatus().isEmpty()) {
            throw new ValidationException("Status is required");
        }
        if (contract.getCategory() == null) {
            throw new ValidationException("Category is required");
        }
        if (contract.getFreelancer() == null) {
            throw new ValidationException("Freelancer is required");
        }
        if (contract.getClient() == null) {
            throw new ValidationException("Client is required");
        }
    }

    //URL: http://localhost:8080/api/favore/v1/contracts
    //Method: POST
    @Transactional
    @PostMapping("/contracts")
    public ResponseEntity<Contract> createContract(Contract contract) {
        validateContract(contract);
        Contract createdContract = contractService.createContract(contract);
        return ResponseEntity.ok(createdContract);
    }

    //URL: http://localhost:8080/api/favore/v1/contracts
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/contracts")
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> contracts = contractService.getAllContracts();
        return ResponseEntity.ok(contracts);
    }

    //URL: http://localhost:8080/api/favore/v1/contracts/{contract_id}
    //Method: GET
    @Transactional
    @GetMapping("/contracts/{contract_id}")
    public ResponseEntity<Contract> getContractById(@PathVariable(value = "contract_id") Long contract_id) {
        Contract contract = contractService.getContractById(contract_id);
        return ResponseEntity.ok(contract);
    }

    //URL: http://localhost:8080/api/favore/v1/contracts/{contract_id}
    //Method: PUT
    @Transactional
    @PutMapping("/contracts/{contract_id}")
    public ResponseEntity<Contract> updateContract(@PathVariable(value = "contract_id") Long contract_id, Contract contract) {
        validateContract(contract);
        Contract updatedContract = contractService.updateContract(contract_id, contract);
        return ResponseEntity.ok(updatedContract);
    }

    //URL: http://localhost:8080/api/favore/v1/contracts/{contract_id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/contracts/{contract_id}")
    public ResponseEntity<Contract> deleteContract(@PathVariable(value = "contract_id") Long contract_id) {
        Contract deletedContract = contractService.deleteContract(contract_id);
        return ResponseEntity.ok(deletedContract);
    }
}
