package ru.skypro.hwstream.service;

import ru.skypro.hwstream.exception.EmployeeNotFoundException;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Map<Integer, List<Employee>> getAllEmployeesByDepartmentId();

    Employee getEmployeeWithMaxSalaryByDepartmentId(Integer departmentId) throws EmployeeNotFoundException;

    Employee getEmployeeWithMinSalaryByDepartmentId(Integer departmentId) throws EmployeeNotFoundException;

    List<Employee> getEmployeesByDepartmentId(Integer departmentId);
}
