package com.vkopendoh.orgapp.to;

import java.time.LocalDate;

public class EmployeeTo {
    private String surname;
    private String name;
    private String patronymic;
    private String sex;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private LocalDate employmentDate;
    private LocalDate retireDate;
    private String position;
    private Integer salary;
    private String department;
    private boolean manager;
    private EmployeeTo supervisor;

    public EmployeeTo(String surname, String name, String sex, LocalDate birthDate,
                      String phone, String email, LocalDate employmentDate,
                      String position, Integer salary, String department) {
        this.surname = surname;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.employmentDate = employmentDate;
        this.position = position;
        this.salary = salary;
        this.department = department;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public LocalDate getRetireDate() {
        return retireDate;
    }

    public void setRetireDate(LocalDate retireDate) {
        this.retireDate = retireDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public EmployeeTo getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(EmployeeTo supervisor) {
        this.supervisor = supervisor;
    }
}
