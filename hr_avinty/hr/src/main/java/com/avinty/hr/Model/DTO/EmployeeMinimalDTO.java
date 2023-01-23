package com.avinty.hr.Model.DTO;

import com.avinty.hr.Model.Entity.Employee;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@RequiredArgsConstructor
public class EmployeeMinimalDTO {

    private Integer id;

    private String fullName;

    private String email;

    private Instant createdAt;

    private Integer createdBy;

    private String position;

    private Instant modifiedAt;

    private Integer modifiedBy;

    public EmployeeMinimalDTO(Employee employee){
        this.id = employee.getId();
        this.fullName = employee.getFullName();
        this.email = employee.getEmail();
        this.createdAt = employee.getCreatedAt();
        this.createdBy = employee.getCreatedBy();
        this.position = employee.getPosition();
        this.modifiedAt = employee.getModifiedAt();
        this.modifiedBy = employee.getModifiedBy();
    }


}