package ru.skypro.hwstream.service;

import ru.skypro.hwstream.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployeesByDepartmentId();

    Employee getEmployeeWithMaxSalaryByDepartmentId(Integer departmentId) throws EmployeeNotFoundException;

    Employee getEmployeeWithMinSalaryByDepartmentId(Integer departmentId) throws EmployeeNotFoundException;

    List<Employee> getEmployeesByDepartmentId(Integer departmentId);
}
