package com.avinty.hr;

import com.avinty.hr.Controller.UserController;
import com.avinty.hr.Model.DTO.LoginDTO;
import com.avinty.hr.Model.DTO.UserDTO;
import com.avinty.hr.Security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersHttpRequestTest {

    @Autowired
    private UserController userController;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void loginResponseTest() throws Exception {
        UserDTO userDTO = new UserDTO(0,"","","");
        LoginDTO response = userController.login(userDTO);
        assertEquals(3, response.getClass().getDeclaredFields().length);
    }


    @Test
    public void loginTest() throws Exception {
        UserDTO userDTO = new UserDTO(1,"admin","admin","admin");
        LoginDTO response = userController.login(userDTO);
        assertEquals(true, response.getJwt().startsWith("Bearer"));

    }


    @Test
    public void validJwtAfterLoginTest() throws Exception {
        UserDTO userDTO = new UserDTO(1,"admin","admin","admin");
        LoginDTO response = userController.login(userDTO);
        boolean valid = jwtUtil.validateJwt(response.getJwt());
        assertEquals(true, valid);
    }

    @Test
    public void validJwtRoleAfterLoginTest() throws Exception {
        UserDTO userDTO = new UserDTO(1,"admin","admin","admin");
        LoginDTO response = userController.login(userDTO);
        String roleFromJwt = jwtUtil.getRoleFromJwt(response.getJwt());
        assertEquals(userDTO.getRole(), roleFromJwt);
    }
}
