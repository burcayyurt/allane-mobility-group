package org.backend.allane.service;

import org.backend.allane.entity.Vehicle;
import org.backend.allane.repository.VehicleRepository;
import org.backend.allane.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class VehicleServiceImplTest {

    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        vehicleService = new VehicleServiceImpl(vehicleRepository);
    }

    @Test
    void createOrEditVehicle_ValidVehicle_ReturnsSavedVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setBrand("Toyota");
        vehicle.setModel("Camry");
        vehicle.setModelYear(2021);
        vehicle.setIdentificationNumber(12345L);
        vehicle.setPrice(BigDecimal.valueOf(25000.0));

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        Vehicle savedVehicle = vehicleService.createVehicle(vehicle);

        assertEquals(vehicle.getId(), savedVehicle.getId());
        assertEquals(vehicle.getBrand(), savedVehicle.getBrand());
        assertEquals(vehicle.getModel(), savedVehicle.getModel());
        assertEquals(vehicle.getModelYear(), savedVehicle.getModelYear());
        assertEquals(vehicle.getIdentificationNumber(), savedVehicle.getIdentificationNumber());
        assertEquals(vehicle.getPrice(), savedVehicle.getPrice());
    }

    @Test
    void isExistsByIdentificationNumber_IdentificationNumberExists_ReturnsTrue() {
        Long identificationNumber = 12345L;

        when(vehicleRepository.existsByIdentificationNumber(identificationNumber)).thenReturn(true);

        boolean result = vehicleService.isExistsByIdentificationNumber(identificationNumber);

        assertEquals(true, result);
    }

    @Test
    void getAllVehicles_ReturnsListOfVehicles() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setBrand("Toyota");
        vehicle.setModel("Camry");
        vehicle.setModelYear(2021);
        vehicle.setIdentificationNumber(12345L);
        vehicle.setPrice(BigDecimal.valueOf(25000.0));

        when(vehicleRepository.findAll()).thenReturn(Collections.singletonList(vehicle));

        List<Vehicle> vehicleList = vehicleService.getAllVehicles();

        assertEquals(1, vehicleList.size());
        assertEquals(vehicle.getId(), vehicleList.get(0).getId());
        assertEquals(vehicle.getBrand(), vehicleList.get(0).getBrand());
        assertEquals(vehicle.getModel(), vehicleList.get(0).getModel());
        assertEquals(vehicle.getModelYear(), vehicleList.get(0).getModelYear());
        assertEquals(vehicle.getIdentificationNumber(), vehicleList.get(0).getIdentificationNumber());
        assertEquals(vehicle.getPrice(), vehicleList.get(0).getPrice());
    }
}
