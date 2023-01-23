package com.avinty.hr;

import com.avinty.hr.Controller.EmployeeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesHttpRequestTest {

    @Autowired
    private EmployeeController employeeController;


    @Test
    public void getAllEmployeesTest() throws Exception {
        List response = employeeController.getAllEmployees(null,null);
        assertEquals(8,response.size());
    }

    @Test
    public void getAllEmployeesByNameTest() throws Exception {
        ArrayList<String> nameArrayList = new ArrayList<>();
        nameArrayList.add("empl");
        List response = employeeController.getAllEmployees(nameArrayList,null);
        assertEquals(6, response.size());
    }

    @Test
    public void getOneEmployeesByNameTest() throws Exception {
        ArrayList<String> nameArrayList = new ArrayList<>();
        nameArrayList.add("employee1");
        List response = employeeController.getAllEmployees(nameArrayList,null);
        assertEquals(1,response.size());
    }

    @Test
    public void getAllEmployeesByEmailTest() throws Exception {
        ArrayList<String> emailArrayList = new ArrayList<>();
        emailArrayList.add("empl");
        List response = employeeController.getAllEmployees(null,emailArrayList);
        assertEquals(6,response.size());
    }

    @Test
    public void getOneEmployeesByEmailTest2() throws Exception {
        ArrayList<String> emailArrayList = new ArrayList<>();
        emailArrayList.add("employee1@asd.asd");
        List response = employeeController.getAllEmployees(null,emailArrayList);
        assertEquals(1,response.size());
    }
}
