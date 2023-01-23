package com.avinty.hr.Controller;


import com.avinty.hr.Model.DTO.DepartmentEmployeesDTO;
import com.avinty.hr.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "dep-emp")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5000")
public class DepartmentEmployeeController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentEmployeesDTO> getAllDepartments(){
        return departmentService.getAllDepartmentWithEmployees();
    }
}
