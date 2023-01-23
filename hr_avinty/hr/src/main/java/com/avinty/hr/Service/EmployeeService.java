package com.avinty.hr.Service;

import com.avinty.hr.Model.DTO.EmployeeDTO;
import com.avinty.hr.Model.DTO.EmployeeMinimalDTO;
import com.avinty.hr.Model.Entity.Employee;
import com.avinty.hr.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public List<EmployeeDTO> getAllEmployees(ArrayList<String> name,ArrayList<String> email){
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<Employee> employeeList = new ArrayList<>();
        if(name != null){
            for (String s : name) {
                employeeList.addAll(employeeRepository.getEmployeesByName(s));
            }
        }
        if(email != null){
            for (String s : email) {
                employeeList.addAll(employeeRepository.getEmployeesByEmail(s));
            }
        }
        if (name==null && email==null){
            employeeList = employeeRepository.findAll();
        }

        for (Employee employee : employeeList) {
            EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }

    @Transactional
    public List<EmployeeMinimalDTO> getEmployeesByDepartment(Integer departmentId){
        List<EmployeeMinimalDTO> employeeMinimalDTOList = new ArrayList<>();
        List<Optional<Employee>> optionalEmployeeList = employeeRepository.getEmployeesByDepartment(departmentId);
        for (int i = 0; i < optionalEmployeeList.size(); i++) {
            if(optionalEmployeeList.get(i).isPresent()){
                EmployeeMinimalDTO employeeMinimalDTO = new EmployeeMinimalDTO(optionalEmployeeList.get(i).get());
                employeeMinimalDTOList.add(employeeMinimalDTO);
            }
        }



        return employeeMinimalDTOList;
    }
}
