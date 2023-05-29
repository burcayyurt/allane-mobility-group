package org.backend.allane.controller;

import org.backend.allane.dto.LeasingContractUpdateDto;
import org.backend.allane.entity.LeasingContract;
import org.backend.allane.entity.Vehicle;
import org.backend.allane.service.ContractOverviewService;
import org.backend.allane.service.LeasingContractService;
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

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/leasingContract")
public class LeasingContractController {

    private LeasingContractService leasingContractService;

    @Autowired
    public LeasingContractController(LeasingContractService leasingContractService) {
        this.leasingContractService = leasingContractService;
    }

    @GetMapping("/{contractNo}")
    public LeasingContractUpdateDto getLeasingContractById(@PathVariable("contractNo") String id){
        return leasingContractService.getLeasingContractByContractNo(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLeasingContract(@RequestBody LeasingContract leasingContract) {

        try {

            if (leasingContractService.isExistsByContractNumber(leasingContract.getContractNumber())) {
                return ResponseEntity.badRequest().body("{\"error\": \"Identification number already exists.\"}");
            }

            if (leasingContract.getContractNumber() == null || leasingContract.getContractNumber() == "") {
                return ResponseEntity.badRequest().body("{\"error\": \"Contract Number field is required.\"}");
            }

            if (leasingContract.getMonthlyRate() == null || leasingContract.getMonthlyRate().equals("")) {
                return ResponseEntity.badRequest().body("{\"error\": \"Monthly rate field is required!\"}");
            }

            if (leasingContract.getCustomer_id() == null || leasingContract.getCustomer_id().equals("")) {
                return ResponseEntity.badRequest().body("{\"error\": \"Customer is required.\"}");
            }

            if (leasingContract.getVehicle_id() == null || leasingContract.getVehicle_id().equals("")) {
                return ResponseEntity.badRequest().body("{\"error\": \"Vehicle is required!\"}");
            }

            LeasingContract savedLeasingContract
                    = leasingContractService.createLeasingContract(leasingContract);
            return ResponseEntity.ok(savedLeasingContract);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{contractNo}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLeasingContract(@PathVariable("contractNo") String id,@RequestBody LeasingContract leasingContract) {

        if (!id.equals(leasingContract.getContractNumber())) {
            return ResponseEntity.badRequest().body("{\"error\": \"Contract Number can not change!\"}");
        }

        try {
            Long leasingContractId = getLeasingContract(id).getId();
            LeasingContract updatedLeasingContract = leasingContractService.updateLeasingContract(leasingContractId, leasingContract);
            return ResponseEntity.ok(updatedLeasingContract);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private LeasingContract getLeasingContract(String contractNo){
        return leasingContractService.getLeasingContract(contractNo);
    }

}
