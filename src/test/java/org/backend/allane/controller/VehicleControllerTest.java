package org.backend.allane.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.backend.allane.entity.Vehicle;
import org.backend.allane.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new VehicleController(vehicleService)).build();
    }

    @Test
    void getAllVehicles_ReturnsListOfVehicles() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setBrand("Toyota");
        vehicle.setModel("Camry");
        vehicle.setModelYear(2021);
        vehicle.setIdentificationNumber(12345L);
        vehicle.setPrice(BigDecimal.valueOf(25000.0));

        when(vehicleService.getAllVehicles()).thenReturn(Collections.singletonList(vehicle));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/vehicle"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand").value("Toyota"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value("Camry"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].modelYear").value(2021))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].identificationNumber").value(12345))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(25000.0));
    }

    @Test
    void saveOrUpdateVehicle_ValidVehicle_ReturnsSavedVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Toyota");
        vehicle.setModel("Camry");
        vehicle.setModelYear(2021);
        vehicle.setIdentificationNumber(12345L);
        vehicle.setPrice(BigDecimal.valueOf(25000.0));

        when(vehicleService.isExistsByIdentificationNumber(any(Long.class))).thenReturn(false);
        when(vehicleService.createVehicle(any(Vehicle.class))).thenReturn(vehicle);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/vehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vehicle)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("Toyota"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Camry"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelYear").value(2021))
                .andExpect(MockMvcResultMatchers.jsonPath("$.identificationNumber").value(12345))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25000.0));
    }
}
