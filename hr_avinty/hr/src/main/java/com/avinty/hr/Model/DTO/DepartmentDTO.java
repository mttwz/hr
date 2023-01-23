package com.avinty.hr.Model.DTO;

import com.avinty.hr.Model.Entity.Department;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@RequiredArgsConstructor
public class DepartmentDTO {

    private Integer id;

    private String name;

    private Integer managerId;

    private Instant createdAt;

    private Integer createdBy;

    private Instant modifiedAt;

    private Integer modifiedBy;

    public DepartmentDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.managerId = department.getManagerId();
        this.createdAt = department.getCreatedAt();
        this.createdBy = department.getCreatedBy();
        this.modifiedAt = department.getModifiedAt();
        this.modifiedBy = department.getModifiedBy();
    }



}

