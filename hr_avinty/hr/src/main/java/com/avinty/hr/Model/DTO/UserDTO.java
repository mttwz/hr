package com.avinty.hr.Model.DTO;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    private String role;

}