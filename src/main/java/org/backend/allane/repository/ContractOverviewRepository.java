package org.backend.allane.repository;

import org.backend.allane.entity.LeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractOverviewRepository extends JpaRepository<LeasingContract, Long> {

    @Query("SELECT lc.contractNumber, CONCAT(c.firstName, ' ', c.lastName) AS Customer," +
            "       CONCAT(v.brand,' ',v.model,'(',v.modelYear,')')" +
            "      AS Vehicle, v.identificationNumber AS VIN," +
            "       lc.monthlyRate AS MonthlyRate, v.price as VehiclePrice " +
            "FROM LeasingContract lc " +
            "JOIN Customer c ON lc.customer_id = c.id " +
            "JOIN Vehicle v ON lc.vehicle_id = v.id")
    List<Object[]> getContractOverviewData();
}