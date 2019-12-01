package com.vkopendoh.orgapp.util;

import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.model.Employee;
import com.vkopendoh.orgapp.to.DepartmentTo;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentUtils {

    public static DepartmentTo getTo(Department department) {
        return new DepartmentTo(department.getName(),
                department.getCreateDate(), getDeptManager(department), department.getEmployees().size());
    }

    private static String getDeptManager(Department department) {
        Department d = department;
        Employee manager = d.getEmployees()
                .stream()
                .filter(Employee::isManager)
                .findAny()
                .orElse(null);
        return manager == null ? "No manager" : manager.getName() + " " + manager.getSurname();
    }

    public static Set<DepartmentTo> getTos(Set<Department> departments) {
        return departments
                .stream()
                .map(DepartmentUtils::getTo)
                .collect(Collectors.toSet());
    }

    public static Set<Department> getAllSubDepartments(Department department, Set<Department> set) {
        Set<Department> result = set;
        if (result == null) {
            result = new HashSet<>();
        }
        if (department.getChildren() != null) {
            for (Department node : department.getChildren()) {
                if (node.getChildren() != null && !node.getChildren().isEmpty()) {
                    result.add(node);
                    getAllSubDepartments(node, result);
                } else {
                    result.add(node);
                }
            }
        }
        return result;
    }


}
