package com.avinty.hr.Service;

import com.avinty.hr.Model.DTO.LoginDTO;
import com.avinty.hr.Model.DTO.UserDTO;
import com.avinty.hr.Model.Entity.User;
import com.avinty.hr.Repository.UserRepository;
import com.avinty.hr.Security.JwtUtil;
import com.avinty.hr.Security.PasswordUtil;
import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordUtil passwordUtil;

    private final JwtUtil jwtUtil;
    public LoginDTO login(UserDTO userDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        LoginDTO loginDTO = new LoginDTO();
        Optional<User> optionalUser = userRepository.findByUsername(userDTO.getUsername());
        if(optionalUser.isPresent() && passwordUtil.validatePassword(optionalUser.get().getPassword(), userDTO.getPassword())){


            loginDTO.setUsername(optionalUser.get().getUsername());
            loginDTO.setRole(optionalUser.get().getRole());
            loginDTO.setJwt(jwtUtil.generateJwt(optionalUser));

        }
        return loginDTO;


    }
}
