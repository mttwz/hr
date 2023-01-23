package com.avinty.hr.Controller;

import com.avinty.hr.Model.DTO.LoginDTO;
import com.avinty.hr.Model.DTO.UserDTO;
import com.avinty.hr.Repository.UserRepository;
import com.avinty.hr.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5000")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginDTO login(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return userService.login(userDTO);
    }



}
