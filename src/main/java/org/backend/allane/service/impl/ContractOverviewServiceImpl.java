package org.backend.allane.service.impl;

import org.backend.allane.dto.ContractOverviewResponseDto;
import org.backend.allane.repository.ContractOverviewRepository;
import org.backend.allane.service.ContractOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractOverviewServiceImpl implements ContractOverviewService {

    private ContractOverviewRepository contractOverviewRepository;

    private List<Object[]> contractOverviewResponseModel;

    @Autowired
    public ContractOverviewServiceImpl(ContractOverviewRepository contractOverviewRepository) {
        this.contractOverviewRepository = contractOverviewRepository;
    }

    @Override
    public List<ContractOverviewResponseDto> getAllContracts() {
        contractOverviewResponseModel = contractOverviewRepository.getContractOverviewData();

        List<ContractOverviewResponseDto> contractOverviewList = new ArrayList<>();

        for (Object[] row : contractOverviewResponseModel) {
            ContractOverviewResponseDto contractOverview = new ContractOverviewResponseDto();
            contractOverview.setContractNo((String) row[0]);
            contractOverview.setCustomer((String) row[1]);
            contractOverview.setVehicle((String) row[2]);
            contractOverview.setVIN((Long) row[3]);
            contractOverview.setMonthlyRate((BigDecimal) row[4]);
            contractOverview.setVehiclePrice((BigDecimal) row[5]);

            contractOverviewList.add(contractOverview);
        }

        return contractOverviewList;
    }
}
