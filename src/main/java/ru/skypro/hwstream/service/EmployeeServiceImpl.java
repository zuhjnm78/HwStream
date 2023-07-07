package ru.skypro.hwstream.service;

import org.springframework.stereotype.Service;
import ru.skypro.hwstream.exception.EmployeeNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service

public class EmployeeServiceImpl implements EmployeeService {
    private Employee employee = new Employee();

    public List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov", 1, 50000),
            new Employee("Vladimir", "Varlamov", 2, 60000),
            new Employee("Ilya", "Vedeneev", 1, 55000),
            new Employee("Gennadi", "Davydow", 3, 70000),
            new Employee("Vitaly", "Dolgov", 4, 80000),
            new Employee("Ivanov", "Gregory", 2, 65000),
            new Employee("Vladislav", "Kartashov", 5, 90000),
            new Employee("Constantin", "Makarov", 4, 75000),
            new Employee("Nikita", "Panfilov", 3, 65000),
            new Employee("Pavel", "Teterin", 5, 95000)
    ));

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDepartmentId() {
        return employees.stream()
                .collect(groupingBy(Employee::getDepartmentId));
    }

    @Override
    public Employee getEmployeeWithMaxSalaryByDepartmentId(Integer departmentId) throws EmployeeNotFoundException {
        return employees.stream()
                .filter(employee -> Objects.equals(employee.getDepartmentId(), departmentId))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("No employee with maximum salary found in department with ID: " + departmentId));
    }
    @Override
    public Employee getEmployeeWithMinSalaryByDepartmentId(Integer departmentId) throws EmployeeNotFoundException {
        return employees.stream()
                .filter(employee -> Objects.equals(employee.getDepartmentId(), departmentId))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("No employee with minimum salary found in department with ID: " + departmentId));
    }


    @Override
    public List<Employee> getEmployeesByDepartmentId(Integer departmentId) {
      return employees.stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }


}
