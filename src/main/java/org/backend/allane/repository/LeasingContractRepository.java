package org.backend.allane.repository;

import org.backend.allane.entity.LeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeasingContractRepository extends JpaRepository<LeasingContract, Long> {


    @Query("SELECT lc.contractNumber, lc.monthlyRate, CONCAT(c.firstName, ' ', c.lastName) AS Customer," +
            "       CONCAT(v.brand,' ',v.model), c.id, v.id "+
            "FROM LeasingContract lc " +
            "JOIN Customer c ON lc.customer_id = c.id " +
            "JOIN Vehicle v ON lc.vehicle_id = v.id WHERE lc.contractNumber = ?1")
    List<Object[]> getLeasingContractByContractNo(String contractNumber);

    @Query("SELECT lc FROM LeasingContract lc WHERE lc.contractNumber = ?1")
    LeasingContract getLeasingContract(String contractNumber);

    boolean existsByContractNumber(String contractNumber);

}
