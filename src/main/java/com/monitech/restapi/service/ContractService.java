package com.monitech.restapi.service;

import java.util.List;
import com.monitech.restapi.model.Contract;

public interface ContractService {

    Contract createContract(Contract contract);

    Contract updateContract(Long contract_id, Contract contract);

    Contract getContractById(Long contract_id);

    Contract deleteContract(Long contract_id);

    List<Contract> getAllContracts();
}
