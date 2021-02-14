package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.configs.constans.Service;
import com.arifinmn.projectapi.configs.constans.Statuses;
import com.arifinmn.projectapi.entities.Customers;
import com.arifinmn.projectapi.entities.Schedules;
import com.arifinmn.projectapi.models.ScheduleModel;
import com.arifinmn.projectapi.models.requests.CustomerRequest;
import com.arifinmn.projectapi.models.requests.ScheduleRequest;
import com.arifinmn.projectapi.models.responses.ScheduleResponse;
import com.arifinmn.projectapi.services.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    @Autowired
    IScheduleService service;

    @GetMapping("/{id}/get")
    public ResponseEntity<?> getScheduleById(@PathVariable Integer id) {
        ScheduleResponse entity = service.getScheduleById(id);
        if (entity == null) {
            return ResponseEntity.ok(ResponseEntity.badRequest());
        }
        return ResponseEntity.ok(entity);
    }

    @GetMapping()
    public ResponseEntity<?> getSchedules() {
        List<ScheduleResponse> entities = service.getAllSchedule();
        return ResponseEntity.ok(entities);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSchedule(@RequestBody @Valid ScheduleModel model) {

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
                throw new RuntimeException("Input service not valid!");
        }

        if (service.createNewSchedule(model)) {
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.ok(
                ResponseEntity.badRequest()
        );

    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateSchedule(@PathVariable Integer id, @RequestBody @Valid ScheduleRequest request) {
        ScheduleModel model = new ScheduleModel();
        model.setStatus(request.getStatus());
        if(service.getScheduleById(id) != null) {
            return ResponseEntity.ok(ResponseEntity.badRequest());
        }

        return ResponseEntity.ok(model);
//
//        switch (model.getStatus().toUpperCase()) {
//            case "PENDING":
//                model.setStatus(Statuses.PENDING.toString());
//                break;
//            case "PROCESS":
//                model.setStatus(Statuses.PROCESS.toString());
//                break;
//            case "SUCCESS":
//                model.setStatus(Statuses.SUCCESS.toString());
//                break;
//            case "FAIL":
//                model.setStatus(Statuses.FAIL.toString());
//                break;
//            default:
//                throw new RuntimeException("Input service not valid!");
//        }
//        model.setId(id);
//
//        if (service.updateSchedule(model)) {
//            return ResponseEntity.ok(model);
//        }
//        return ResponseEntity.ok(
//                ResponseEntity.badRequest()
//        );

    }


}
