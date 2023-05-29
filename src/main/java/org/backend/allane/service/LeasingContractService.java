package org.backend.allane.service;

import org.backend.allane.dto.LeasingContractUpdateDto;
import org.backend.allane.entity.LeasingContract;

public interface LeasingContractService {

    LeasingContractUpdateDto getLeasingContractByContractNo(String id);
    LeasingContract createLeasingContract(LeasingContract leasingContract);
    LeasingContract updateLeasingContract(Long id, LeasingContract leasingContract);
    boolean isExistsByContractNumber(String contractNumber);
    LeasingContract getLeasingContract(String id);


}
