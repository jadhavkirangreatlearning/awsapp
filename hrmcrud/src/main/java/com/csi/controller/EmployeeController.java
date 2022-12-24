package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @GetMapping
    public String sayHello(){
        return "WELCOME TO FINTECH CSI PUNE";
    }

    @PostMapping("/savedata")
    public Employee saveData(@RequestBody Employee employee){
        return employeeServiceImpl.saveData(employee);
    }

    @GetMapping("/getdatabyid/{empId}")
    public Optional<Employee> getDataById(@PathVariable int empId){
        return employeeServiceImpl.getDataById(empId);
    }

    @GetMapping("/getalldata")
    public List<Employee> getAllData(){
        return employeeServiceImpl.getAllData();
    }

    @PutMapping("/updatedata/{empId}")
    public Employee updateData(@PathVariable int empId, @RequestBody Employee employee){
        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(()-> new RecordNotFoundException("Employee Id Does not Exist"));


        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDOB(employee.getEmpDOB());

        return employeeServiceImpl.updateData(employee1);
    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public String deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return "Data Deleted Successfully";
    }

}
