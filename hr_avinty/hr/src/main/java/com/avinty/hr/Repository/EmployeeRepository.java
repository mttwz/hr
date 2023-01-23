package com.avinty.hr.Repository;

import com.avinty.hr.Model.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    @Query("select e from Employee e where e.department.id = :departmentId")
    List<Optional<Employee>> getEmployeesByDepartment(@Param("departmentId") Integer departmentId);

    @Query("select e from Employee e where e.fullName like :name%")
    List<Employee> getEmployeesByName(@Param("name") String name);

    @Query("select e from Employee e where e.email like :email%")
    List<Employee> getEmployeesByEmail(@Param("email") String email);



}
