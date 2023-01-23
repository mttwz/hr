package com.avinty.hr;

import com.avinty.hr.Controller.DepartmentController;
import com.avinty.hr.Repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DepartmentsHttpRequestTest {

    @Autowired
    private DepartmentController departmentController;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void listDepartmentDropdownTest() throws Exception {
        String name = "dep";
        List response = departmentController.listDepartmentDropdown(name);
        assertEquals(3,response.size());
    }

    @Test
    public void listOneDepartmentDropdownTest() throws Exception {
        String name = "department1";
        List response = departmentController.listDepartmentDropdown(name);
        assertEquals(1,response.size());
    }

    @Test
    public void deleteDepartmentTest() throws Exception {
        int size = departmentRepository.getAllDepartment().size();
        departmentController.deleteDepartement(1);
        int sizeAfterDelete = departmentRepository.getAllDepartment().size();
        assertEquals(sizeAfterDelete+1, size);
    }


}
