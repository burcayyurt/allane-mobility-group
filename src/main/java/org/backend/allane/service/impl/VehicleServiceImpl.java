package org.backend.allane.service.impl;

import org.backend.allane.entity.Customer;
import org.backend.allane.entity.Vehicle;
import org.backend.allane.repository.VehicleRepository;
import org.backend.allane.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public boolean isExistsByIdentificationNumber(Long identificationNumber) {
        return vehicleRepository.existsByIdentificationNumber(identificationNumber);
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.getById(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {

        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setId(Long.valueOf(id));
        updatedVehicle.setBrand(vehicle.getBrand());
        updatedVehicle.setModel(vehicle.getModel());
        updatedVehicle.setPrice(vehicle.getPrice());
        updatedVehicle.setModelYear(vehicle.getModelYear());
        updatedVehicle.setIdentificationNumber(vehicle.getIdentificationNumber());

        return vehicleRepository.save(updatedVehicle);
    }
}
