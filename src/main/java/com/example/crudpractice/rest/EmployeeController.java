package com.example.crudpractice.rest;

import com.example.crudpractice.entity.Employee;
import com.example.crudpractice.services.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee find(@PathVariable int employeeId) {
        return employeeService.find(employeeId);

    }

    @PostMapping("/employee")
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }
    @PutMapping("/employee/{employeeId}")
    public Employee update(@RequestBody Employee employee,@PathVariable int employeeId){
        Employee tempEmployee = employeeService.update(employee,employeeId);
        return tempEmployee;
    }

    @DeleteMapping("/employee/{employeeId}")
    public void delete(@PathVariable int employeeId) {
        employeeService.delete(employeeId);
    }


}
