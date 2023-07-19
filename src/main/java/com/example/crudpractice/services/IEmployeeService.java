package com.example.crudpractice.services;
import com.example.crudpractice.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    Employee find(int id);

    Employee save(Employee employee);
    Employee update(Employee employee,int id);
    void delete(int id);
}
