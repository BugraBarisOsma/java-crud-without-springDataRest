package com.example.crudpractice.services;

import com.example.crudpractice.dao.IEmployeeRepository;
import com.example.crudpractice.entity.Employee;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee find(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee = null;
        if (result.isPresent()) {
            employee = result.get();
        } else {
            throw new RuntimeException("Did not find employee with id :" + id);
        }

        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee, int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        employeeRepository.delete(result.get());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void delete(int id) {
        employeeRepository.deleteById(id);

    }

    public void downloadExcel(HttpServletResponse response, List<Employee> employeeList) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("EmployeeList");

        Row headerRow = sheet.createRow(3);
        headerRow.createCell(0).setCellValue("First Name");
        headerRow.createCell(1).setCellValue("Last Name");
        headerRow.createCell(2).setCellValue("Email");

        int rowNum = 1;
        for (Employee rowData : employeeList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowData.getFirstName());
            row.createCell(1).setCellValue(rowData.getLastName());
            row.createCell(2).setCellValue(rowData.getEmail());
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=EmployeeList.xlsx");
        response.setHeader("Cache-Control", CacheControl.noCache().getHeaderValue());
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
    }

}
