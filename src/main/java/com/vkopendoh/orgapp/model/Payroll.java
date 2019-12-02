package com.vkopendoh.orgapp.model;

public class Payroll {
    private final String departmentName;
    private final Integer payroll;

    public Payroll(String departmentName, Integer payroll) {
        this.departmentName = departmentName;
        this.payroll = payroll;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Integer getPayroll() {
        return payroll;
    }
}
