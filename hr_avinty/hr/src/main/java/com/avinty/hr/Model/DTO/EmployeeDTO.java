package com.avinty.hr.Model.DTO;

import com.avinty.hr.Model.Entity.Employee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

@Getter
@Setter
@RequiredArgsConstructor
public class EmployeeDTO {



    private Integer id;

    private String fullName;

    private String email;

    private DepartmentDTO department;

    private Instant createdAt;

    private Integer createdBy;

    private String position;

    private Instant modifiedAt;

    private Integer modifiedBy;

    public EmployeeDTO(Employee employee){
        this.id = employee.getId();
        this.fullName = employee.getFullName();
        this.email = employee.getEmail();
        if(employee.getDepartment() != null) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            BeanUtils.copyProperties(employee.getDepartment(), departmentDTO);
            this.department = departmentDTO;
        }else this.department = new DepartmentDTO();
        this.createdAt = employee.getCreatedAt();
        this.createdBy = employee.getCreatedBy();
        this.position = employee.getPosition();
        this.modifiedAt = employee.getModifiedAt();
        this.modifiedBy = employee.getModifiedBy();
    }


    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", position='" + position + '\'' +
                ", modifiedAt=" + modifiedAt +
                ", modifiedBy=" + modifiedBy +
                '}';
    }
}