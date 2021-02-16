package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.models.ScheduleModel;
import com.arifinmn.projectapi.models.responses.ScheduleResponse;

import java.util.List;

public interface IScheduleRepository {
    void save(ScheduleModel schedule);

    boolean removeById(Integer id);

    ScheduleResponse findById(Integer id);

    ScheduleResponse findByCustomerId(Integer id);

    List<ScheduleResponse> findAll();

    Integer count();
}
