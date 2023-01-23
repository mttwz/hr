package com.avinty.hr.Service;

import com.avinty.hr.Model.DTO.DepartmentDTO;
import com.avinty.hr.Model.DTO.DepartmentEmployeesDTO;
import com.avinty.hr.Model.DTO.EmployeeMinimalDTO;
import com.avinty.hr.Model.Entity.Department;
import com.avinty.hr.Model.Entity.Employee;
import com.avinty.hr.Repository.DepartmentRepository;
import com.avinty.hr.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeService employeeService;

    private final EmployeeRepository employeeRepository;

    @Transactional
    public List<DepartmentEmployeesDTO> getAllDepartment(){
        List<DepartmentEmployeesDTO> departmentEmployeesDTOList = new ArrayList<>();
        List<Department> departmentList = departmentRepository.findAll();
        for (int i = 0; i < departmentList.size(); i++) {
            DepartmentEmployeesDTO departmentEmployeesDTO = new DepartmentEmployeesDTO(departmentList.get(i));
            departmentEmployeesDTOList.add(departmentEmployeesDTO);
        }
        return departmentEmployeesDTOList;
    }


    @Transactional
    public List<DepartmentEmployeesDTO> getAllDepartmentWithEmployees(){
        List<DepartmentEmployeesDTO> departmentEmployeesDTOList = getAllDepartment();
        for (int i = 0; i < departmentEmployeesDTOList.size(); i++) {
            List<EmployeeMinimalDTO> employeeMinimalDTOList = employeeService.getEmployeesByDepartment(departmentEmployeesDTOList.get(i).getId());
            departmentEmployeesDTOList.get(i).setEmployeeDTO(employeeMinimalDTOList);
        }
        return departmentEmployeesDTOList;
    }

    @Transactional
    public List<DepartmentDTO> getDepartmentByName(String name){
        List<Department> departmentList = new ArrayList<>();
        if(name != null){
            departmentList = departmentRepository.getDepartmentByName(name);
        }else {
            departmentList = departmentRepository.findAll();
        }
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (int i = 0; i < departmentList.size(); i++) {
            DepartmentDTO departmentDTO = new DepartmentDTO(departmentList.get(i));
            departmentDTOList.add(departmentDTO);
        }
        return departmentDTOList;
    }

    public void setManager(Integer employeeId,DepartmentDTO departmentDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentDTO.getId());

        if(optionalEmployee.isPresent() && optionalEmployee.get().getDepartment() != null){
            Optional<Department> optionalDepartmentPrevious = departmentRepository.findById(optionalEmployee.get().getDepartment().getId());
            optionalDepartmentPrevious.ifPresent(department -> department.setManagerId(null));
        }




        if(optionalDepartment.isPresent() && optionalEmployee.isPresent() && optionalEmployee.get().getPosition().equalsIgnoreCase("manager")){
            optionalDepartment.get().setManagerId(employeeId);
            optionalEmployee.get().setDepartment(optionalDepartment.get());
            departmentRepository.save(optionalDepartment.get());
            employeeRepository.save( optionalEmployee.get());
        }
    }

    public void deleteDepartment(Integer departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        List<Optional<Employee>> optionalEmployeeList = employeeRepository.getEmployeesByDepartment(departmentId);
        for (int i = 0; i < optionalEmployeeList.size(); i++) {
            if (optionalEmployeeList.get(i).isPresent()){
                Employee employee = optionalEmployeeList.get(i).get();
                employee.setDepartment(null);
                employeeRepository.save(employee);
            }
        }

        if(optionalDepartment.isPresent()){
            Department department = optionalDepartment.get();
            departmentRepository.delete(department);
        }
    }
}
