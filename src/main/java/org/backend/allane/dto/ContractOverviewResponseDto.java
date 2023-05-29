package org.backend.allane.dto;

import java.math.BigDecimal;

public class ContractOverviewResponseDto {

    private String contractNo;
    private String customer;
    private String vehicle;
    private Long VIN;
    private BigDecimal monthlyRate;
    private BigDecimal vehiclePrice;

    public ContractOverviewResponseDto() {
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public Long getVIN() {
        return VIN;
    }

    public void setVIN(Long VIN) {
        this.VIN = VIN;
    }

    public BigDecimal getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(BigDecimal monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public BigDecimal getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(BigDecimal vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }
}
