package org.backend.allane.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.backend.allane.entity.LeasingContract;
import org.backend.allane.service.ContractOverviewService;
import org.backend.allane.service.LeasingContractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LeasingContractControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LeasingContractService leasingContractService;

    @Mock
    private ContractOverviewService contractOverviewService;

    @InjectMocks
    private LeasingContractController leasingContractController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(leasingContractController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSaveOrUpdateLeasingContract_Success() throws Exception {
        LeasingContract leasingContract = new LeasingContract();
        leasingContract.setContractNumber("LC001");
        leasingContract.setMonthlyRate(BigDecimal.valueOf(1000));
        leasingContract.setCustomer_id(1L);
        leasingContract.setVehicle_id(1L);

        when(leasingContractService.createLeasingContract(leasingContract)).thenReturn(leasingContract);

        mockMvc.perform(post("/api/leasingContract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(leasingContract)))
                .andExpect(status().isOk());

        verify(leasingContractService, times(1)).createLeasingContract(leasingContract);
    }

    @Test
    void testSaveOrUpdateLeasingContract_InvalidContractNumber() throws Exception {
        LeasingContract leasingContract = new LeasingContract();
        leasingContract.setMonthlyRate(BigDecimal.valueOf(1000));
        leasingContract.setCustomer_id(1L);
        leasingContract.setVehicle_id(1L);

        mockMvc.perform(post("/api/leasingContract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(leasingContract)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveOrUpdateLeasingContract_InvalidMonthlyRate() throws Exception {
        LeasingContract leasingContract = new LeasingContract();
        leasingContract.setContractNumber("LC001");
        leasingContract.setCustomer_id(1L);
        leasingContract.setVehicle_id(1L);

        mockMvc.perform(post("/api/leasingContract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(leasingContract)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveOrUpdateLeasingContract_InvalidCustomer() throws Exception {
        LeasingContract leasingContract = new LeasingContract();
        leasingContract.setContractNumber("LC001");
        leasingContract.setMonthlyRate(BigDecimal.valueOf(1000));
        leasingContract.setVehicle_id(1L);

        mockMvc.perform(post("/api/leasingContract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(leasingContract)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveOrUpdateLeasingContract_InvalidVehicle() throws Exception {
        LeasingContract leasingContract = new LeasingContract();
        leasingContract.setContractNumber("LC001");
        leasingContract.setMonthlyRate(BigDecimal.valueOf(1000));
        leasingContract.setCustomer_id(1L);

        mockMvc.perform(post("/api/leasingContract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(leasingContract)))
                .andExpect(status().isBadRequest());
    }

}
