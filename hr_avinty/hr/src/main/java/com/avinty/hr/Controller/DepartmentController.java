package com.avinty.hr.Controller;

import com.avinty.hr.Model.DTO.DepartmentDTO;
import com.avinty.hr.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "department")
@CrossOrigin(origins = "http://localhost:5000")
public class DepartmentController {

    private final DepartmentService departmentService;



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentDTO> listDepartmentDropdown(@RequestParam(required = false) String name){
        return departmentService.getDepartmentByName(name);
    }
    
    @PatchMapping(path = "/set-manager/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public void setManager(@PathVariable Integer employeeId, @RequestBody DepartmentDTO departmentDTO) {
        departmentService.setManager(employeeId,departmentDTO);
    }

    @DeleteMapping(path = "{departmentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDepartement(@PathVariable Integer departmentId) {
        departmentService.deleteDepartment(departmentId);
    }
}
