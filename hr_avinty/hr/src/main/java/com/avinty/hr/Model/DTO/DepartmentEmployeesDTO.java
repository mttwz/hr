package com.avinty.hr.Model.DTO;

import com.avinty.hr.Model.Entity.Department;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class DepartmentEmployeesDTO {

    private Integer id;

    private String name;

    private Instant createdAt;

    private Integer createdBy;

    private Instant modifiedAt;

    private Integer modifiedBy;

    private List<EmployeeMinimalDTO> employeeDTO;


    public DepartmentEmployeesDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.createdAt = department.getCreatedAt();
        this.createdBy = department.getCreatedBy();
        this.modifiedAt = department.getModifiedAt();
        this.modifiedBy = department.getModifiedBy();
    }
}
