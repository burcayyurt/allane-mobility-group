package org.backend.allane.service;

import org.backend.allane.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    Vehicle getVehicleById(Long id);

    List<Vehicle> getAllVehicles();

    Vehicle createVehicle(Vehicle vehicle);

    boolean isExistsByIdentificationNumber(Long identificationNumber);

    Vehicle updateVehicle(Long id, Vehicle vehicle);

}
