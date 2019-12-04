package com.vkopendoh.orgapp.service;

import com.vkopendoh.orgapp.model.*;
import com.vkopendoh.orgapp.repository.DepartmentRepository;
import com.vkopendoh.orgapp.to.DepartmentTo;
import com.vkopendoh.orgapp.util.DepartmentUtils;
import com.vkopendoh.orgapp.util.exception.NotFoundException;
import com.vkopendoh.orgapp.util.exception.ViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository repository;

    @Autowired
    HistoryService historyService;

    @Transactional
    public Set<DepartmentTo> getAll() {
        return DepartmentUtils.getTos(new HashSet<>(repository.findAll()));
    }

    public DepartmentTo getTo(int id) {
        return DepartmentUtils.getTo(get(id));
    }

    public DepartmentTo getToByName(String name) {
        return DepartmentUtils.getTo(repository.findByName(name).orElseThrow(() -> new NotFoundException("Department with name: " + name + " not found")));
    }

    @Transactional
    public Set<DepartmentTo> getChildren(int id) {
        Set<Department> ch = get(id).getChildren();
        return DepartmentUtils.getTos(ch);
    }

    public Set<DepartmentTo> getAllSubDepartmens(int id) {
        return DepartmentUtils.getTos(DepartmentUtils.getAllSubDepartments(get(id), null));
    }

    public Set<DepartmentTo> getAllParentDepartmens(int id) {
        Set<Department> result = new HashSet<>();
        Department department = get(id);
        Department parent = department.getParent();
        while (parent != null) {
            result.add(parent);
            parent = parent.getParent();
        }
        return DepartmentUtils.getTos(result);
    }

    private Department get(int id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Department with id: " + id + " not found"));
    }

    @Transactional
    public void delete(int id) {
        Department department = get(id);
        if (department.getEmployees().isEmpty()) {
            historyService.save(new History(Action.DELETE, department.getName(), LocalDateTime.now()));
            repository.delete(department);

        } else {
            throw new ViolationException("Can't delete because there are employees in department with id: " + id);
        }

    }

    @Transactional
    public void rename(String newName, int id) {
        Department department = get(id);
        department.setName(newName);
        historyService.save(new History(Action.RENAME, department.getName(), LocalDateTime.now()));
        repository.save(department);
    }

    @Transactional
    public Department create(Department department) {
        department.setCreateDate(LocalDate.now());
        historyService.save(new History(Action.CREATE, department.getName(), LocalDateTime.now()));
        return repository.save(department);
    }

    @Transactional
    public void setParent(Integer parentId, int id) {
        Department department = get(id);
        department.getChildren().stream()
                .forEach(d -> {
                    d.setParent(department.getParent());
                    repository.save(d);
                });
        Department parent = get(parentId);
        department.setParent(parent);
        historyService.save(new History(Action.REPLACE, department.getName(), LocalDateTime.now()));
        repository.save(department);
    }

    public Payroll getPayroll(int id) {
        Department department = get(id);
        Integer payroll = department.getEmployees()
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
        return new Payroll(department.getName(), payroll);
    }
}
