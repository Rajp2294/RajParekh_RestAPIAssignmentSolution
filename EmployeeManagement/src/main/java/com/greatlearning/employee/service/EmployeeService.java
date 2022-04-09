package com.greatlearning.employee.service;

import java.util.List;

import com.greatlearning.employee.entity.Employee;
import com.greatlearning.employee.entity.Role;
import com.greatlearning.employee.entity.User;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	
	public Employee findEmployeeById(long empId);

	public Employee save(Employee employee);

	public Employee updateEmployee(Employee employee);

	public String deleteEmployeeById(long empId);

	public List<Employee> fetchEmployeesByName(String firstName);

	public List<Employee> sortByFirstName(String order);

	public User saveUser(User user);

	public Role saveRole(Role role);

		
}
