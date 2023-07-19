package com.example.crudpractice.dao;
import com.example.crudpractice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {

}
