package org.backend.allane.controller;

import org.backend.allane.dto.ContractOverviewResponseDto;
import org.backend.allane.service.ContractOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contractOverview")
public class ContractOverviewController {

    private ContractOverviewService contractOverviewService;

    @Autowired
    public ContractOverviewController(ContractOverviewService contractOverviewService) {
        this.contractOverviewService = contractOverviewService;
    }

    @GetMapping
    public List<ContractOverviewResponseDto> getAllCustomers(){
        return contractOverviewService.getAllContracts();
    }
}
