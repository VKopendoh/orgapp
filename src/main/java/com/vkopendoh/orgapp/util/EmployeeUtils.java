package com.vkopendoh.orgapp.util;

import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.model.Employee;

public class EmployeeUtils {
    public final static String RU_NAME_PATTERN = "[а-яёА-ЯЁ-]+";
    public final static String RU_NAME_MSG = "Use only cyrillic chars for ";
    public final static String PHONE_PATTERN = "[0-9 ()+-]+";
    public final static String PHONE_MSG = "Use only next symbols for phone number: " + PHONE_PATTERN;

    private static Employee getDeptManager(Department department) {
        return department.getEmployees()
                .stream()
                .filter(Employee::isManager)
                .findAny()
                .orElse(null);
    }
}
