package com.avinty.hr.Model.Entity;

import com.avinty.hr.Model.DTO.DepartmentDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
@Getter
@Setter
@Entity
@Table(name = "departments")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private Integer id;

    @Column(name = "name", nullable = true, length = 50)
    private String name;

    @Column(name = "manager_id", nullable = true)
    private Integer managerId;

    @Column(name = "created_at", nullable = true)
    private Instant createdAt;

    @Column(name = "created_by", nullable = true)
    private Integer createdBy;

    @Column(name = "modified_at")
    private Instant modifiedAt;

    @Column(name = "modified_by")
    private Integer modifiedBy;


    public Department(DepartmentDTO departmentDTO) {
        this.id = departmentDTO.getId();
        this.name = departmentDTO.getName();
        this.managerId = departmentDTO.getManagerId();
        this.createdAt = departmentDTO.getCreatedAt();
        this.createdBy = departmentDTO.getCreatedBy();
        this.modifiedAt = departmentDTO.getModifiedAt();
        this.modifiedBy = departmentDTO.getModifiedBy();
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", managerId=" + managerId +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", modifiedAt=" + modifiedAt +
                ", modifiedBy=" + modifiedBy +
                '}';
    }
}