package com.vkopendoh.orgapp.service;

import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.model.Employee;
import com.vkopendoh.orgapp.model.Payroll;
import com.vkopendoh.orgapp.repository.DepartmentRepository;
import com.vkopendoh.orgapp.repository.PayrollRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {
    Logger log = LoggerFactory.getLogger(PayrollService.class);

    private final PayrollRepository payrollRepository;

    private final DepartmentRepository departmentRepository;


    @Autowired
    public PayrollService(PayrollRepository payrollRepository, DepartmentRepository departmentRepository) {
        this.payrollRepository = payrollRepository;
        this.departmentRepository = departmentRepository;
    }

    @Async
    @Scheduled(fixedRate = 300000)
    public void loadPayroll() {
        log.info("=====> Started refresh payroll data....");
        List<Department> departments = departmentRepository.getAll();
        departments.forEach(department -> {
            Payroll payroll;
            if (department.getEmployees().isEmpty()) {
                payroll = new Payroll(department.getId(), department.getName(), 0, department);
            } else {
                payroll = getPayroll(department);
            }
            payrollRepository.save(payroll);
        });
        log.info("=====> Data refreshed....");
    }

    private Payroll getPayroll(Department department) {
        Integer payroll = department.getEmployees()
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
        return new Payroll(department.getId(), department.getName(), payroll, department);
    }
}
