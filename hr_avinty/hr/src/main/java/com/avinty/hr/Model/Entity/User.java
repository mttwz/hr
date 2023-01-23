package com.avinty.hr.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private Integer id;

    @Column(name = "username", nullable = true, length = 100)
    private String username;

    @Column(name = "password", nullable = true, length = 100)
    private String password;

    @Column(name = "role", nullable = true, length = 50)
    private String role;

}