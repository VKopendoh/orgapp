package com.vkopendoh.orgapp.util;

import com.vkopendoh.orgapp.exception.NotFoundException;
import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.model.Employee;
import com.vkopendoh.orgapp.to.DepartmentTo;

import java.util.ArrayList;
import java.util.List;

public class DepartmentUtils {

    public static DepartmentTo getTo(Department department) throws NotFoundException {
        return new DepartmentTo(department.getName(),
                department.getCreateDate(), getDeptManager(department), department.getEmployees().size());
    }

    private static String getDeptManager(Department department) throws NotFoundException {
        Department d = department;
        return d.getEmployees()
                .stream()
                .filter(Employee::isManager)
                .findAny()
                .orElseThrow(NotFoundException::new).getName();
    }

    public static List<Department> getAllSubDepartments(Department department, List<Department> list) {
        List<Department> result = list;
        if (result == null) {
            result = new ArrayList<>();
        }
        if (department.getChildrens() != null) {
            for (Department node : department.getChildrens()) {
                if (node.getChildrens() != null && !node.getChildrens().isEmpty()) {
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
