package com.vkopendoh.orgapp.util;

import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.model.Employee;
import com.vkopendoh.orgapp.to.EmployeeTo;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeUtils {
    public final static String RU_NAME_PATTERN = "[а-яёА-ЯЁ-]+";
    public final static String RU_NAME_MSG= "Use only cyrillic chars for ";
    public final static String PHONE_PATTERN = "[0-9 ()+-]+";
    public final static String PHONE_MSG= "Use only next symbols for phone number: " + PHONE_PATTERN;

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
