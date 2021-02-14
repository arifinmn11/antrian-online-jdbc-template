package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.configs.constans.Service;
import com.arifinmn.projectapi.entities.Customers;
import com.arifinmn.projectapi.models.requests.CustomerRequest;
import com.arifinmn.projectapi.models.searchs.CustomerSearch;
import com.arifinmn.projectapi.services.ICustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    ICustomerService service;

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerRequest request) {
        Customers entity = new Customers();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());

        switch (request.getService().toUpperCase()) {
            case "PENGADUAN":
                entity.setService(Service.PENGADUAN);
                break;
            case "PENDAFTARAN":
                entity.setService(Service.PENDAFTARAN_KTP);
                break;
            case "PERBAIKAN":
                entity.setService(Service.PERBAIKAN_KTP);
                break;
            default:
                throw new RuntimeException("Input service not valid!");
        }

        if (service.createNewCustomer(entity)) {
            return ResponseEntity.ok(entity);
        }
        return ResponseEntity.ok(
                ResponseEntity.badRequest()
        );

    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateCustomerById(@RequestBody @Valid CustomerRequest request, @PathVariable Integer id) {
        Customers entity = new Customers();
        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());

        switch (request.getService().toUpperCase()) {
            case "PENGADUAN":
                entity.setService(Service.PENGADUAN);
                break;
            case "PENDAFTARAN":
                entity.setService(Service.PENDAFTARAN_KTP);
                break;
            case "PERBAIKAN":
                entity.setService(Service.PERBAIKAN_KTP);
                break;
            default:
                throw new RuntimeException("Input service not valid!");
        }

        if (service.createNewCustomer(entity)) {
            return ResponseEntity.ok(entity);
        }
        return ResponseEntity.ok(
                ResponseEntity.badRequest()
        );
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        Customers entity = service.getCustomerById(id);
        if (entity == null) {
            return ResponseEntity.ok(ResponseEntity.badRequest());
        }
        return ResponseEntity.ok(entity);
    }

    @GetMapping()
    public ResponseEntity<?> getCustomers(CustomerSearch search) {
        return ResponseEntity.ok(service.getAllCustomer());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Integer id) {
        if(service.removeCustomerById(id)) {
            return ResponseEntity.ok("Has been deleted!");
        }
        return ResponseEntity.ok(ResponseEntity.badRequest());
    }

}
