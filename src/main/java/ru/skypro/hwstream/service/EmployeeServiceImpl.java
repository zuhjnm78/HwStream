package ru.skypro.hwstream.service;

import org.springframework.stereotype.Service;
import ru.skypro.hwstream.exception.EmployeeNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

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
    private static final int MAX_EMPLOYEES = 50;
    private  final Integer DEPARTMENT_COUNT = 5;


    @Override
    public List<Employee> getAllEmployeesByDepartmentId() {
        List<Employee> allEmployees = new ArrayList<>();

        for (int i = 1; i <= DEPARTMENT_COUNT; i++) {
            final int departmentId = i;
            List<Employee> employeesInDepartment = employees.stream()
                    .filter(employee -> employee.getDepartmentId() == departmentId)
                    .collect(Collectors.toList());

            allEmployees.addAll(employeesInDepartment);

            System.out.println("Department " + i + ":");
            employeesInDepartment.forEach(employee -> System.out.println(employee));
        }

        return allEmployees;
    }

    @Override
    public Employee getEmployeeWithMaxSalaryByDepartmentId(Integer departmentId) throws EmployeeNotFoundException {
        List<Employee> employeesInDepartment = getEmployeesByDepartmentId(departmentId);

        if (employeesInDepartment.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found in department with ID: " + departmentId);
        }

        Optional<Employee> maxSalaryEmployee = employeesInDepartment.stream()
                .max(Comparator.comparingInt(employee -> employee.getSalary()));

        if (maxSalaryEmployee.isPresent()) {
            return maxSalaryEmployee.get();
        } else {
            throw new EmployeeNotFoundException("No employee with maximum salary found in department with ID: " + departmentId);
        }
    }
    @Override
    public Employee getEmployeeWithMinSalaryByDepartmentId(Integer departmentId) throws EmployeeNotFoundException {
        List<Employee> employeesInDepartment = getEmployeesByDepartmentId(departmentId);

        if (employeesInDepartment.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found in department with ID: " + departmentId);
        }

        Optional<Employee> minSalaryEmployee = employeesInDepartment.stream()
                .min(Comparator.comparingInt(Employee::getSalary));

        if (minSalaryEmployee.isPresent()) {
            return minSalaryEmployee.get();
        } else {
            throw new EmployeeNotFoundException("No employee with minimum salary found in department with ID: " + departmentId);
        }
    }


    @Override
    public List<Employee> getEmployeesByDepartmentId(Integer departmentId) {
      return employees.stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }


}
