package org.backend.allane.service;

import org.backend.allane.dto.ContractOverviewResponseDto;

import java.util.List;

public interface ContractOverviewService {

    List<ContractOverviewResponseDto> getAllContracts();
}
