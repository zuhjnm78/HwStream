package ru.skypro.hwstream.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.skypro.hwstream.exception.EmployeeNotFoundException;
import ru.skypro.hwstream.service.Employee;
import ru.skypro.hwstream.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }
    @GetMapping("/max-salary")
    @NonNull
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> getEmployeeWithMaxSalaryByDepartmentId(@RequestParam(required = false) Integer departmentId) {
        try {
            Employee employee = employeeService.getEmployeeWithMaxSalaryByDepartmentId(departmentId);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/min-salary")
    @NonNull
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> getEmployeeWithMinSalaryByDepartmentId(@RequestParam(required = false) Integer departmentId) {
        try {
            Employee employee = employeeService.getEmployeeWithMinSalaryByDepartmentId(departmentId);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@RequestParam Integer departmentId) {
        List<Employee> employees = employeeService.getEmployeesByDepartmentId(departmentId);

        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(employees);
        }
    }
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Employee>> getAllEmployeesByDepartment() {
        List<Employee> employees = employeeService.getAllEmployeesByDepartmentId();

        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(employees);
        }
    }
}