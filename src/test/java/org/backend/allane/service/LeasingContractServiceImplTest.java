package org.backend.allane.service;

import org.backend.allane.entity.LeasingContract;
import org.backend.allane.repository.LeasingContractRepository;
import org.backend.allane.service.impl.LeasingContractServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class LeasingContractServiceImplTest {

    @Mock
    private LeasingContractRepository leasingContractRepository;

    @InjectMocks
    private LeasingContractServiceImpl leasingContractService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrEditLeasingContract() {
        LeasingContract leasingContract = new LeasingContract();
        leasingContract.setContractNumber("LC001");

        when(leasingContractRepository.save(leasingContract)).thenReturn(leasingContract);

        LeasingContract savedLeasingContract = leasingContractService.createLeasingContract(leasingContract);

        verify(leasingContractRepository, times(1)).save(leasingContract);
        Assertions.assertEquals(leasingContract, savedLeasingContract);
    }
}
