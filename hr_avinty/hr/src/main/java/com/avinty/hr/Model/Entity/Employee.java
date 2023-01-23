package com.avinty.hr.Model.Entity;

import com.avinty.hr.Model.DTO.EmployeeDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private Integer id;

    @Column(name = "full_name", nullable = true, length = 100)
    private String fullName;

    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "created_at", nullable = true)
    private Instant createdAt;

    @Column(name = "created_by", nullable = true)
    private Integer createdBy;

    @Column(name = "\"position\"", length = 50)
    private String position;

    @Column(name = "modified_at")
    private Instant modifiedAt;

    @Column(name = "modified_by")
    private Integer modifiedBy;


    public Employee(EmployeeDTO employeeDTO) {
        this.id = employeeDTO.getId();
        this.fullName = employeeDTO.getFullName();
        this.email = employeeDTO.getEmail();

        this.createdAt = employeeDTO.getCreatedAt();
        this.createdBy = employeeDTO.getCreatedBy();
        this.position = employeeDTO.getPosition();
        this.modifiedAt = employeeDTO.getModifiedAt();
        this.modifiedBy = employeeDTO.getModifiedBy();
    }

    @Override
    public String toString() {
        return "Employee{" +
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