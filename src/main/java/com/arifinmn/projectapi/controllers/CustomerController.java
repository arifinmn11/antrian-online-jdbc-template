package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.configs.constans.Service;
import com.arifinmn.projectapi.entities.Customers;
import com.arifinmn.projectapi.exceptions.ApplicationExceptions;
import com.arifinmn.projectapi.exceptions.EntityNotFoundException;
import com.arifinmn.projectapi.models.requests.CustomerRequest;
import com.arifinmn.projectapi.models.searchs.CustomerSearch;
import com.arifinmn.projectapi.services.ICustomerService;
import com.arifinmn.projectapi.services.IRegisterCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import com.arifinmn.projectapi.models.responses.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    ICustomerService service;

    @Autowired
    IRegisterCustomerService registerService;

    @PostMapping("/register")
    public ResponseMessage<?> registerCustomer(@RequestBody CustomerRequest request) {
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
                throw new ApplicationExceptions(HttpStatus.BAD_REQUEST, "input service not valid!");
        }

        registerService.createNewCustomer(entity);

        return ResponseMessage.success(entity);
    }

    @PostMapping("/create")
    public ResponseMessage<?> createCustomer(@RequestBody CustomerRequest request) {
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
                throw new ApplicationExceptions(HttpStatus.BAD_REQUEST, "input service not valid!");
        }

        service.createNewCustomer(entity);

        return ResponseMessage.success(entity);
    }

    @PutMapping("/{id}/update")
    public ResponseMessage<?> updateCustomerById(@RequestBody @Valid CustomerRequest request, @PathVariable Integer id) {
        Customers entity = new Customers();
        entity.setId(id);
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
                throw new ApplicationExceptions(HttpStatus.BAD_REQUEST, "Input service not valid!");
        }
        service.updateCustomer(entity);

        return ResponseMessage.success(entity);

    }

    @GetMapping("/{id}/get")
    public ResponseMessage<?> getCustomerById(@PathVariable Integer id) {
        Customers entity = service.getCustomerById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }

    @GetMapping()
    public ResponseMessage<?> getCustomers(CustomerSearch search) {
        List<Customers> customerList = service.getAllCustomer();
        List<Customers> filterList = customerList.stream()
                .filter(c -> c.getName().startsWith(search.getName()) ||
                        c.getEmail().startsWith(search.getEmail()) ||
                        c.getService().toString().startsWith(search.getService()) ||
                        c.getPhone().startsWith(search.getPhone())
                )
                .collect(Collectors.toList());
        return ResponseMessage.success(filterList);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseMessage<?> deleteCustomerById(@PathVariable Integer id) {
        if (service.removeCustomerById(id)) {
            return ResponseMessage.success("Has been deleted!");
        }
        throw new ApplicationExceptions(HttpStatus.INTERNAL_SERVER_ERROR, "Entity not delete!!");
    }

}
