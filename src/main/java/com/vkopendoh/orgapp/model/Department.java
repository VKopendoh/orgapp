package com.vkopendoh.orgapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", unique = true)
    private String name;

    private LocalDate createDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Department parent;

    @NotNull
    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<Department> children = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    public Department(String name, LocalDate createDate, Department parent,
                      Set<Department> children, List<Employee> employees) {
        this.name = name;
        this.createDate = createDate;
        this.parent = parent;
        this.children = children;
        this.employees = employees;
    }

    public Department(Department parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public Department() {
    }

    private void registerInParentsChilds() {
        this.parent.children.add(this);
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

    public Set<Department> getChildren() {
        return children;
    }

    public void setChildrens(Set<Department> children) {
        this.children = children;
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
                ", childrens=" + children +
                ", employees=" + employees +
                '}';
    }
}
