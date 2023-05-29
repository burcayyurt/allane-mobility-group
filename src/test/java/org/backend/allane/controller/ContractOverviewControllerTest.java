package org.backend.allane.controller;

import org.backend.allane.dto.ContractOverviewResponseDto;
import org.backend.allane.service.ContractOverviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContractOverviewControllerTest {

    private ContractOverviewController contractOverviewController;

    @Mock
    private ContractOverviewService contractOverviewService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        contractOverviewController = new ContractOverviewController(contractOverviewService);
    }

    @Test
    public void testGetAllContracts() {
        List<ContractOverviewResponseDto> contractOverviewList = new ArrayList<>();

        ContractOverviewResponseDto contractOverview1 = new ContractOverviewResponseDto();
        contractOverview1.setContractNo("CONTRACT1");
        contractOverview1.setCustomer("Burcay Yurt");
        contractOverview1.setVehicle("Mitsubishi Lancer Evo");
        contractOverview1.setVIN(123456789L);
        contractOverview1.setMonthlyRate(new BigDecimal("500.00"));
        contractOverview1.setVehiclePrice(new BigDecimal("25000.00"));
        contractOverviewList.add(contractOverview1);

        ContractOverviewResponseDto contractOverview2 = new ContractOverviewResponseDto();
        contractOverview2.setContractNo("CONTRACT2");
        contractOverview2.setCustomer("Simge Yurt");
        contractOverview2.setVehicle("Volvo XC90");
        contractOverview2.setVIN(987654321L);
        contractOverview2.setMonthlyRate(new BigDecimal("400.00"));
        contractOverview2.setVehiclePrice(new BigDecimal("20000.00"));
        contractOverviewList.add(contractOverview2);

        when(contractOverviewService.getAllContracts()).thenReturn(contractOverviewList);

        List<ContractOverviewResponseDto> response = contractOverviewController.getAllCustomers();

        assertEquals(2, response.size());
        assertEquals("Burcay Yurt", response.get(0).getCustomer());

        verify(contractOverviewService, times(1)).getAllContracts();
    }
}
