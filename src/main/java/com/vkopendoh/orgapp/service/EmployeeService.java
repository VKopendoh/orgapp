package com.vkopendoh.orgapp.service;

import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.model.Employee;
import com.vkopendoh.orgapp.repository.DepartmentRepository;
import com.vkopendoh.orgapp.repository.EmployeeRepository;
import com.vkopendoh.orgapp.util.exception.NotFoundException;
import com.vkopendoh.orgapp.util.exception.ViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository repository, DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    public Employee getFetch(int id) {
        return repository.get(id);
    }

    public List<Employee> getAll(int departmentId) {
        return repository.getAll(departmentId);
    }

    @Transactional
    public Employee create(Employee employee) {
        validateData(employee);
        return repository.save(employee);
    }


    @Transactional
    public void update(Employee employee, int id) {
        Employee updated = getFetch(id);
        if (updated == null) {
            throw new NotFoundException("Can't update, employee with id: " + id + " not found");
        }
        employee.setId(id);
        validateData(employee);
        repository.save(employee);
    }

    @Transactional
    public Employee retire(LocalDate date, int id) {
        Employee updated = getFetch(id);
        if (updated == null) {
            throw new NotFoundException("Can't retire, employee with id: " + id + " not found");
        }
        updated.setDepartment(null);
        updated.setManager(false);
        updated.setSalary(0);
        updated.setRetireDate(date);
        validateDates(updated);
        return repository.save(updated);
    }

    @Transactional
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
        validateData(updated);
        return repository.save(updated);
    }

    @Transactional
    public List<Employee> transferAll(int oldDepartmentId, int newId) {
        List<Employee> employees = getAll(oldDepartmentId);
        Department newDepartment = departmentRepository.findById(newId)
                .orElseThrow(() -> new NotFoundException("Can't transfer, department with id: "
                        + newId
                        + " not found"));
        employees.forEach(employee -> {
            employee.setDepartment(newDepartment);
            validateData(employee);
        });
        newDepartment.setEmployees(employees);
        departmentRepository.save(newDepartment);
        return employees;
    }

    public Employee getManager(int id) {
        Employee employee = getFetch(id);
        int departmentId = employee.getDepartment().getId();
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Can't transfer, department with id: "
                        + departmentId
                        + " not found"));
        List<Employee> employees = department.getEmployees();
        return employees.stream().filter(Employee::isManager).findAny().orElse(null);
    }

    public List<Employee> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Employee> findByBirthDate(LocalDate birthDate) {
        return repository.findByBirthDate(birthDate);
    }

    private void validateData(Employee employee) {
        validateDates(employee);
        int departmentId = employee.getDepartment().getId();
        Employee manager = getManager(departmentId);
        if (employee.isManager() && manager != null) {
            throw new ViolationException("Can't be more then one manager in department, current manager's id: "
                    + manager.getId());
        }
        if (manager != null && manager.getSalary() < employee.getSalary()) {
            throw new ViolationException("Employee salary can't be greater then managers, which is now: "
                    + manager.getSalary());
        }
    }

    private void validateDates(Employee employee) {
        LocalDate empDate = employee.getEmploymentDate();
        LocalDate birthDate = employee.getBirthDate();
        LocalDate retireDate = employee.getRetireDate();
        if (empDate.compareTo(birthDate) < 1) {
            throw new ViolationException("Employment date can't be earlier then birth date");
        }
        if (retireDate != null && retireDate.compareTo(empDate) < 1) {
            throw new ViolationException("Employment date can't be later then retire date");
        }
    }
}
