package com.vkopendoh.orgapp.util;

import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.model.Employee;
import com.vkopendoh.orgapp.to.EmployeeTo;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeUtils {

    public static EmployeeTo getTo(Employee employee) {
        return new EmployeeTo(employee.getSurname(), employee.getName(), employee.getSex().name(),
                employee.getBirthDate(), employee.getPhone(), employee.getEmail(), employee.getEmploymentDate(),
                employee.getPosition().getName(), employee.getSalary(), employee.getDepartment().getName());
    }

    public static List<EmployeeTo> getTos(List<Employee> employees) {
        return employees
                .stream()
                .map(EmployeeUtils::getTo)
                .collect(Collectors.toList());
    }

    private static Employee getDeptManager(Department department) {
        return department.getEmployees()
                .stream()
                .filter(Employee::isManager)
                .findAny()
                .orElse(null);
    }
}
