package com.monitech.restapi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monitech.restapi.model.Contract;
import com.monitech.restapi.repository.ContractRepository;
import com.monitech.restapi.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Long contract_id, Contract contract) {
        if (contractRepository.existsById(contract_id)) {
            contract.setContract_id(contract_id);
            return contractRepository.save(contract);
        }
        return null;
    }

    @Override
    public Contract getContractById(Long contract_id) {
        return contractRepository.findById(contract_id).orElse(null);
    }

    @Override
    public Contract deleteContract(Long contract_id) {
        Contract contract = getContractById(contract_id);
        if (contract != null) {
            contractRepository.delete(contract);
            return contract;
        }
        return null;
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
}
