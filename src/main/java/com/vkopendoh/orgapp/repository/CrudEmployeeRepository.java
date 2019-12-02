package com.vkopendoh.orgapp.repository;

import com.vkopendoh.orgapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface CrudEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e JOIN FETCH e.position JOIN FETCH e.department " +
            "WHERE e.department.id=:departmentId " +
            "ORDER BY e.surname,e.name ASC ")
    List<Employee> getAll(@Param("departmentId") int departmentId);

    @Query("SELECT e FROM Employee e JOIN FETCH e.position JOIN FETCH e.department WHERE e.id=:id ")
    Employee get(@Param("id") int id);

    @Override
    @Transactional
    Employee save(Employee item);
}
