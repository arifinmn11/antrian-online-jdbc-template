package com.arifinmn.projectapi.services.impls;

import com.arifinmn.projectapi.configs.constans.Statuses;
import com.arifinmn.projectapi.entities.Customers;
import com.arifinmn.projectapi.models.ScheduleModel;
import com.arifinmn.projectapi.repositories.impls.CustomerRepositoryImpl;
import com.arifinmn.projectapi.repositories.impls.ScheduleRepositoryImpl;
import com.arifinmn.projectapi.services.IRegisterCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegisterCustomerServiceImpl implements IRegisterCustomerService {

    @Autowired
    CustomerRepositoryImpl customerRepository;

    @Autowired
    ScheduleRepositoryImpl scheduleRepository;

    @Override
    @Transactional
    public void createNewCustomer(Customers customer) {
        ScheduleModel schedule = new ScheduleModel();
        schedule.setStatus(Statuses.PENDING.toString());
        customerRepository.save(customer);
        schedule.setCustomer_id(customer.getId());
        scheduleRepository.save(schedule);
    }
}
