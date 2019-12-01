package com.vkopendoh.orgapp.service;

import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.repository.CrudDepartmentRepository;
import com.vkopendoh.orgapp.to.DepartmentTo;
import com.vkopendoh.orgapp.util.DepartmentUtils;
import com.vkopendoh.orgapp.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class DepartmentService {
    @Autowired
    CrudDepartmentRepository repository;

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

}
