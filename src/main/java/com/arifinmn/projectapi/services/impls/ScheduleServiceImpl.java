package com.arifinmn.projectapi.services.impls;

import com.arifinmn.projectapi.entities.Schedules;
import com.arifinmn.projectapi.models.ScheduleModel;
import com.arifinmn.projectapi.models.responses.ScheduleResponse;
import com.arifinmn.projectapi.repositories.IScheduleRepository;
import com.arifinmn.projectapi.services.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements IScheduleService {
    @Autowired
    IScheduleRepository repository;

    @Override
    public void createBulkSchedule(List<Schedules> schedules) {

    }

    @Override
    public void createNewSchedule(ScheduleModel schedule) {
        repository.save(schedule);
    }

    @Override
    public void updateSchedule(ScheduleModel schedule) {
        repository.save(schedule);
    }

    @Override
    public boolean removeScheduleById(Integer id) {
        return repository.removeById(id);
    }

    @Override
    public ScheduleResponse getScheduleById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<ScheduleResponse> getAllSchedule() {
        return repository.findAll();
    }

    @Override
    public Integer getTotalSchedule() {
        return repository.count();
    }
}
