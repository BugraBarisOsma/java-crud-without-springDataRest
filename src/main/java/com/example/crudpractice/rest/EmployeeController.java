package com.example.crudpractice.rest;

import com.example.crudpractice.entity.Employee;
import com.example.crudpractice.services.EmployeeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    //Listing employees for view (thymeleaf)
    @GetMapping("/employee/list")
    public String findAllList(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "thymeMap";
    }

    //Adding new employee via thymeleaf so this method sends req to showformadd.html form page
    @GetMapping("/employee/list/showFormAdd")
    public String showFormAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("addEmployee", employee);

        return "showformadd";
    }

    //in form page, get properties and create a new employee.
    @PostMapping("/employee/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/api/employee/list";
    }

    @GetMapping("/employee/list/showFormUpdate")
    public String showFormUpdate(@RequestParam("employeeId") int id, Model model) {
        Employee employeeTemp = employeeService.find(id);
        model.addAttribute("addEmployee", employeeTemp);

        return "showformadd";
    }
    @GetMapping("/employee/list/showFormDelete")
    public String showFormDelete(@RequestParam("employeeId") int id,Model model){
        Employee employee = employeeService.find(id);
        employeeService.delete(employee.getId());
        model.addAttribute("addEmployee", employee);

        return "redirect:/api/employee/list";
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
    public Employee update(@RequestBody Employee employee, @PathVariable int employeeId) {
        Employee tempEmployee = employeeService.update(employee, employeeId);
        return tempEmployee;
    }

    @DeleteMapping("/employee/{employeeId}")
    public void delete(@PathVariable int employeeId) {
        employeeService.delete(employeeId);
    }


}
