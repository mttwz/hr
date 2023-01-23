package com.avinty.hr.Repository;

import com.avinty.hr.Model.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    @Query("select d from Department d where d.name like :name%")
    List<Department> getDepartmentByName(@Param("name") String name);

    @Query("select d from Department d")
    List<Department> getAllDepartment();

}
