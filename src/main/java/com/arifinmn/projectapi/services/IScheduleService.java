package com.arifinmn.projectapi.services;

import com.arifinmn.projectapi.entities.Schedules;
import com.arifinmn.projectapi.models.ScheduleModel;
import com.arifinmn.projectapi.models.responses.ScheduleResponse;

import java.util.List;

public interface IScheduleService {
    void createBulkSchedule(List<Schedules> schedules);

    void createNewSchedule(ScheduleModel schedule);

    void updateSchedule(ScheduleModel schedule);

    boolean removeScheduleById(Integer id);

    ScheduleResponse getScheduleById(Integer id);

    List<ScheduleResponse> getAllSchedule();

    Integer getTotalSchedule();
}
