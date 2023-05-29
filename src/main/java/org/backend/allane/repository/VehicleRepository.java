package org.backend.allane.repository;

import org.backend.allane.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    boolean existsByIdentificationNumber(Long identificationNumber);

}
