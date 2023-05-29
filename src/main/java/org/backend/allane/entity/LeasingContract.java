package org.backend.allane.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LeasingContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String contractNumber;

    @NonNull
    private BigDecimal monthlyRate;

    @NonNull
    private Long customer_id;

    @NonNull
    private Long vehicle_id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public BigDecimal getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(BigDecimal monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(@NonNull Long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeasingContract that = (LeasingContract) o;
        return Objects.equals(id, that.id) && Objects.equals(contractNumber, that.contractNumber) && Objects.equals(monthlyRate, that.monthlyRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contractNumber, monthlyRate);
    }

    @Override
    public String toString() {
        return "LeasingContractEntity{" +
                "id=" + id +
                ", contractNumber='" + contractNumber + '\'' +
                ", monthlyRate=" + monthlyRate +
                '}';
    }
}
