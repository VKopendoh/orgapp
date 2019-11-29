package com.vkopendoh.orgapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDate createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Department parent;

    @NotNull
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Department> childrens = new LinkedList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public Department(String name, LocalDate createDate, Department parent,
                      List<Department> childrens, List<Employee> employees) {
        this.name = name;
        this.createDate = createDate;
        this.parent = parent;
        this.childrens = childrens;
        this.employees = employees;
    }

    public Department(String name, LocalDate createDate) {
        this.name = name;
        this.createDate = createDate;
    }

    public Department() {
    }

    private void registerInParentsChilds() {
        this.parent.childrens.add(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }

    public List<Department> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Department> childrens) {
        this.childrens = childrens;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", parent=" + parent +
                ", childrens=" + childrens +
                ", employees=" + employees +
                '}';
    }
}
