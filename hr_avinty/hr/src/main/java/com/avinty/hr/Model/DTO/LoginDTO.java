package com.avinty.hr.Model.DTO;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginDTO {

    private String username;

    private String role;

    private String jwt;
}
