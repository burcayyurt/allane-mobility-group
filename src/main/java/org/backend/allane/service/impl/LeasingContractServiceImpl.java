package org.backend.allane.service.impl;

import org.backend.allane.dto.ContractOverviewResponseDto;
import org.backend.allane.dto.LeasingContractUpdateDto;
import org.backend.allane.entity.LeasingContract;
import org.backend.allane.entity.Vehicle;
import org.backend.allane.repository.LeasingContractRepository;
import org.backend.allane.service.LeasingContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LeasingContractServiceImpl implements LeasingContractService {

    private LeasingContractRepository leasingContractRepository;

    @Autowired
    public LeasingContractServiceImpl(LeasingContractRepository leasingContractRepository) {
        this.leasingContractRepository = leasingContractRepository;
    }


    @Override
    public LeasingContractUpdateDto getLeasingContractByContractNo(String id) {
        LeasingContractUpdateDto leasingContractUpdateDto = new LeasingContractUpdateDto();
        List<LeasingContractUpdateDto> leasingContractUpdateDtos = new ArrayList<>();

        List<Object[]> contractOverviewResponseModel = leasingContractRepository.getLeasingContractByContractNo(id);
        for (Object[] row : contractOverviewResponseModel) {

            leasingContractUpdateDto.setContractNo((String) row[0]);
            leasingContractUpdateDto.setMonthlyRate((BigDecimal) row[1]);
            leasingContractUpdateDto.setCustomer((String) row[2]);
            leasingContractUpdateDto.setVehicle((String) row[3]);
            leasingContractUpdateDto.setCustomerId((Long) row[4]);
            leasingContractUpdateDto.setVehicleId((Long) row[5]);

            leasingContractUpdateDtos.add(leasingContractUpdateDto);
        }

        return leasingContractUpdateDtos.get(0);
    }

    @Override
    public LeasingContract createLeasingContract(LeasingContract leasingContract) {
       return leasingContractRepository.save(leasingContract);
    }

    @Override
    public LeasingContract updateLeasingContract(Long id, LeasingContract leasingContract) {

        LeasingContract updatedLeasingContract = new LeasingContract();
        updatedLeasingContract.setId(Long.valueOf(id));
        updatedLeasingContract.setContractNumber(leasingContract.getContractNumber());
        updatedLeasingContract.setCustomer_id(leasingContract.getCustomer_id());
        updatedLeasingContract.setMonthlyRate(leasingContract.getMonthlyRate());
        updatedLeasingContract.setVehicle_id(leasingContract.getVehicle_id());

        return leasingContractRepository.save(updatedLeasingContract);
    }

    @Override
    public boolean isExistsByContractNumber(String contractNumber) {
        return leasingContractRepository.existsByContractNumber(contractNumber);
    }

    @Override
    public LeasingContract getLeasingContract(String id) {
        return leasingContractRepository.getLeasingContract(id);
    }


}
