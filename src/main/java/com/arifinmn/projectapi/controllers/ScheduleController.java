package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.configs.constans.Statuses;
import com.arifinmn.projectapi.entities.Customers;
import com.arifinmn.projectapi.exceptions.ApplicationExceptions;
import com.arifinmn.projectapi.exceptions.EntityNotFoundException;
import com.arifinmn.projectapi.models.ScheduleModel;
import com.arifinmn.projectapi.models.requests.ScheduleRequest;
import com.arifinmn.projectapi.models.responses.ResponseMessage;
import com.arifinmn.projectapi.models.responses.ScheduleResponse;
import com.arifinmn.projectapi.models.searchs.ScheduleSearch;
import com.arifinmn.projectapi.services.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    @Autowired
    IScheduleService service;

    @GetMapping("/{id}/get")
    public ResponseMessage<?> getScheduleById(@PathVariable Integer id) {
        ScheduleResponse entity = service.getScheduleById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }

    @GetMapping()
    public ResponseMessage<?> getSchedules(ScheduleSearch search) {
        List<ScheduleResponse> entities = service.getAllSchedule();
        List<ScheduleResponse> filterList = entities.stream()
                .filter(c -> c.getName().startsWith(search.getName()) &&
                        c.getEmail().startsWith(search.getEmail()) &&
                        c.getService().toString().startsWith(search.getService()) &&
                        c.getPhone().startsWith(search.getPhone())
                )
                .collect(Collectors.toList());
        return ResponseMessage.success(filterList);
    }

    @PostMapping("/create")
    public ResponseMessage<?> createSchedule(@RequestBody @Valid ScheduleModel model) {
        switch (model.getStatus().toUpperCase()) {
            case "PENDING":
                model.setStatus(Statuses.PENDING.toString());
                break;
            case "PROCESS":
                model.setStatus(Statuses.PROCESS.toString());
                break;
            case "SUCCESS":
                model.setStatus(Statuses.SUCCESS.toString());
                break;
            case "FAIL":
                model.setStatus(Statuses.FAIL.toString());
                break;
            default:
                throw new ApplicationExceptions(HttpStatus.BAD_REQUEST, "Input status not valid!");
        }
        service.createNewSchedule(model);
        return ResponseMessage.success(model);
    }

    @PutMapping("/{id}/update")
    public ResponseMessage<?> updateSchedule(@PathVariable Integer id, @RequestBody @Valid ScheduleRequest request) {
        ScheduleModel model = new ScheduleModel();
        model.setId(id);
        model.setStatus(request.getStatus());

        if (service.getScheduleById(id) != null) {
            throw new EntityNotFoundException();
        }

        switch (model.getStatus().toUpperCase()) {
            case "PENDING":
                model.setStatus(Statuses.PENDING.toString());
                break;
            case "PROCESS":
                model.setStatus(Statuses.PROCESS.toString());
                break;
            case "SUCCESS":
                model.setStatus(Statuses.SUCCESS.toString());
                break;
            case "FAIL":
                model.setStatus(Statuses.FAIL.toString());
                break;
            default:
                throw new ApplicationExceptions(HttpStatus.BAD_REQUEST, "Input service not valid!");
        }

        return ResponseMessage.success(model);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseMessage<?> deleteCustomerById(@PathVariable Integer id) {
        if (service.removeScheduleById(id)) {
            return ResponseMessage.success("Has been deleted!");
        }
        throw new ApplicationExceptions(HttpStatus.INTERNAL_SERVER_ERROR, "Entity not delete!!");
    }

}
