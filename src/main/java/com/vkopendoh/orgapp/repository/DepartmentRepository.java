package com.vkopendoh.orgapp.repository;

import com.vkopendoh.orgapp.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByName(String name);

    @Query("SELECT d FROM Department d JOIN FETCH d.employees")
    List<Department> getAll();
}
