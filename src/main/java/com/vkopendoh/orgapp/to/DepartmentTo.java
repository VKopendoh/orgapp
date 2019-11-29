package com.vkopendoh.orgapp.to;

import java.time.LocalDate;

public class DepartmentTo {
    private final String name;
    private final LocalDate createDate;
    private final String manager;
    private final int emlployeesQuantity;

    public DepartmentTo(String name, LocalDate createDate, String manager, int emlployeesQuantity) {
        this.name = name;
        this.createDate = createDate;
        this.manager = manager;
        this.emlployeesQuantity = emlployeesQuantity;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public String getManager() {
        return manager;
    }

    public int getEmlployeesQuantity() {
        return emlployeesQuantity;
    }

    @Override
    public String toString() {
        return "DepartmentTo{" +
                "name='" + name + '\'' +
                ", createDate=" + createDate +
                ", manager='" + manager + '\'' +
                ", emlployeesQuantity=" + emlployeesQuantity +
                '}';
    }
}
