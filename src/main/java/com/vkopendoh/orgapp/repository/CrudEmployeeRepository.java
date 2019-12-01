package com.vkopendoh.orgapp.repository;

import com.vkopendoh.orgapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudEmployeeRepository extends JpaRepository<Employee, Integer> {
}
