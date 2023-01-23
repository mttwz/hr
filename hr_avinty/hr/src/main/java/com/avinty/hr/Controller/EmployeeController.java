package com.avinty.hr.Controller;

import com.avinty.hr.Model.DTO.EmployeeDTO;
import com.avinty.hr.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "employees")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5000")
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(value = "name", required = false) ArrayList<String> name, @RequestParam(value = "email", required = false) ArrayList<String> email) {
        return employeeService.getAllEmployees(name,email);
    }
}
