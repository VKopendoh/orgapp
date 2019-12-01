package com.vkopendoh.orgapp.repository;

import com.vkopendoh.orgapp.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrudDepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByName(String name);
}
