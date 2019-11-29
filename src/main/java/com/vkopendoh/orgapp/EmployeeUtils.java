package com.vkopendoh.orgapp;

import com.vkopendoh.orgapp.model.Department;
import com.vkopendoh.orgapp.model.Employee;
import com.vkopendoh.orgapp.model.Position;
import com.vkopendoh.orgapp.model.Sex;

import java.time.LocalDate;

public class EmployeeUtils {
    public static final Employee EMPLOYEE1 = new Employee("Иванов", "Иван", "Иванович", Sex.MALE, LocalDate.now(),
            "phone is 555", "email@mm", LocalDate.now(), LocalDate.now(), 100);
    public static final Position POS_EMP = new Position("jus employee");
    public static final LocalDate LOCAL_DATE = LocalDate.of(2018, 10, 12);
    public static final Department DPT_MAIN = new Department("depMain", LOCAL_DATE);

}
