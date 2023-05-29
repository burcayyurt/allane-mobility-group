package org.backend.allane.controller;

import org.backend.allane.entity.Customer;
import org.backend.allane.entity.Vehicle;
import org.backend.allane.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable("id") Long id){
        return vehicleService.getVehicleById(id);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @PostMapping
    public ResponseEntity<?> saveVehicle(@RequestBody Vehicle vehicle) {

        try {

            if (vehicleService.isExistsByIdentificationNumber(vehicle.getIdentificationNumber())) {
                return ResponseEntity.badRequest().body("{\"error\": \"Identification number already exists.\"}");
            }

            if (vehicle.getBrand() == null || vehicle.getBrand().isEmpty()) {
                return ResponseEntity.badRequest().body("{\"error\": \"Brand field is required!\"}");
            }

            if (vehicle.getModel() == null || vehicle.getModel().isEmpty()) {
                return ResponseEntity.badRequest().body("{\"error\": \"Model field is required!\"}");
            }

            if (vehicle.getPrice() == null) {
                return ResponseEntity.badRequest().body("{\"error\": \"Price field is required!\"}");
            }

            if (vehicle.getModelYear() == null) {
                return ResponseEntity.badRequest().body("{\"error\": \"Model year field is required!\"}");
            }

            if (vehicle.getModelYear() < 1980 || vehicle.getModelYear() > 2023) {
                return ResponseEntity.badRequest().body("{\"error\": \"Model year should between 1980-2023!\"}");
            }

            Vehicle savedVehicle = vehicleService.createVehicle(vehicle);
            return ResponseEntity.ok(savedVehicle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateVehicle(@PathVariable("id") String id, @RequestBody Vehicle vehicle) {
        try {
            Vehicle updatedVehicle = vehicleService.updateVehicle(Long.valueOf(id), vehicle);
            return ResponseEntity.ok(updatedVehicle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
