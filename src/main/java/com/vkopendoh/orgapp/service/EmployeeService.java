package com.vkopendoh.orgapp.service;

import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.model.Employee;
import com.vkopendoh.orgapp.repository.CrudDepartmentRepository;
import com.vkopendoh.orgapp.repository.CrudEmployeeRepository;
import com.vkopendoh.orgapp.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    CrudEmployeeRepository repository;

    @Autowired
    CrudDepartmentRepository departmentRepository;

    public Employee getFetch(int id) {
        return repository.get(id);
    }

    public List<Employee> getAll(int departmentId) {
        return repository.getAll(departmentId);
    }

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public void update(Employee employee, int id) {
        Employee updated = getFetch(id);
        if (updated == null) {
            throw new NotFoundException("Can't update, employee with id: " + id + " not found");
        }
        employee.setId(id);
        repository.save(employee);
    }

    public Employee retire(LocalDate date, int id) {
        Employee updated = getFetch(id);
        if (updated == null) {
            throw new NotFoundException("Can't retire, employee with id: " + id + " not found");
        }
        updated.setRetireDate(date);
        return repository.save(updated);
    }

    public Employee transfer(int departmentId, int id) {
        Employee updated = getFetch(id);
        if (updated == null) {
            throw new NotFoundException("Can't transfer, employee with id: " + id + " not found");
        }
        Department newDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Can't transfer, department with id: "
                        + departmentId
                        + " not found"));
        updated.setDepartment(newDepartment);
        return repository.save(updated);
    }

    public List<Employee> transferAll(int oldDepartmentId, int newId) {
        List<Employee> employees = getAll(oldDepartmentId);
        Department newDepartment = departmentRepository.findById(newId)
                .orElseThrow(() -> new NotFoundException("Can't transfer, department with id: "
                        + newId
                        + " not found"));
        employees.forEach(employee -> employee.setDepartment(newDepartment));
        newDepartment.setEmployees(employees);
        departmentRepository.save(newDepartment);
        return employees;
    }

    @Transactional
    public Employee getManager(int id) {
        Employee employee = getFetch(id);
        int departmentId = employee.getDepartment().getId();
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Can't transfer, department with id: "
                        + departmentId
                        + " not found"));
        List<Employee> employees = department.getEmployees();
        Employee manager = employees.stream().filter(Employee::isManager).findAny().orElse(null);
        int ids = manager.getId();
        return getFetch(ids);
    }
}
