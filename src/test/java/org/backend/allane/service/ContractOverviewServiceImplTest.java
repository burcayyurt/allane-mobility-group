package org.backend.allane.service;

import org.backend.allane.dto.ContractOverviewResponseDto;
import org.backend.allane.repository.ContractOverviewRepository;
import org.backend.allane.service.impl.ContractOverviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContractOverviewServiceImplTest {

    private ContractOverviewService contractOverviewService;

    @Mock
    private ContractOverviewRepository contractOverviewRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        contractOverviewService = new ContractOverviewServiceImpl(contractOverviewRepository);
    }

    @Test
    public void testGetAllContracts() {
        List<Object[]> contractOverviewResponseModel = new ArrayList<>();

        Object[] row1 = {"CONTRACT1", "Burcay Yurt", "Mitsubishi Lancer Evo", 123456789L, new BigDecimal("500.00"), new BigDecimal("25000.00")};
        contractOverviewResponseModel.add(row1);

        Object[] row2 = {"CONTRACT2", "Simge Yurt", "Volvo XC90", 987654321L, new BigDecimal("400.00"), new BigDecimal("20000.00")};
        contractOverviewResponseModel.add(row2);

        when(contractOverviewRepository.getContractOverviewData()).thenReturn(contractOverviewResponseModel);

        List<ContractOverviewResponseDto> contractOverviewList = contractOverviewService.getAllContracts();

        assertEquals(contractOverviewResponseModel.size(), contractOverviewList.size());

        ContractOverviewResponseDto contractOverview1 = contractOverviewList.get(0);
        assertEquals("CONTRACT1", contractOverview1.getContractNo());
        assertEquals("Burcay Yurt", contractOverview1.getCustomer());
        assertEquals("Mitsubishi Lancer Evo", contractOverview1.getVehicle());
        assertEquals(123456789L, contractOverview1.getVIN());
        assertEquals(new BigDecimal("500.00"), contractOverview1.getMonthlyRate());
        assertEquals(new BigDecimal("25000.00"), contractOverview1.getVehiclePrice());

        ContractOverviewResponseDto contractOverview2 = contractOverviewList.get(1);
        assertEquals("CONTRACT2", contractOverview2.getContractNo());
        assertEquals("Simge Yurt", contractOverview2.getCustomer());
        assertEquals("Volvo XC90", contractOverview2.getVehicle());
        assertEquals(987654321L, contractOverview2.getVIN());
        assertEquals(new BigDecimal("400.00"), contractOverview2.getMonthlyRate());
        assertEquals(new BigDecimal("20000.00"), contractOverview2.getVehiclePrice());

        verify(contractOverviewRepository, times(1)).getContractOverviewData();
    }
}
