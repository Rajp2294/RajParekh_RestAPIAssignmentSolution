package com.greatlearning.employee.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employee.entity.Employee;
import com.greatlearning.employee.entity.Role;
import com.greatlearning.employee.entity.User;
import com.greatlearning.employee.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;		
	}
	// Add the user dynamically
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return this.employeeService.saveUser(user);
	}
	
	// Add the role dynamically
	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return this.employeeService.saveRole(role);
	}
	
	// Fetches a list of all employees
	@GetMapping("/employees")
	public List<Employee> fetchAllEmployees() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return this.employeeService.getAllEmployees();
	}
	
	// Adds a new employee
	@PostMapping("/employees")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return this.employeeService.save(employee);
	}
	
	// Gets employee by given id
	@GetMapping("/employees/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") long empId) {
		return this.employeeService.findEmployeeById(empId);
	}
	
	// Updates the employee with id 
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") long employeeId) {
		return this.employeeService.updateEmployee(employee);
	}
	
	// Deletes the employee with id
	@DeleteMapping("/employees/{id}")
	public String deleteEmployeeById(@PathVariable("id") long empId) {
		return this.employeeService.deleteEmployeeById(empId);
	}
	
	// Searches the employee from the list based on first name
	@GetMapping("/employees/search/{firstName}")
	public List<Employee> fetchByName(@PathVariable String firstName){
		return this.employeeService.fetchEmployeesByName(firstName);
	}
	
	// Sorts the list of employee based on first name in ascending / descending
	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName(@RequestParam(name="order") String order){
		return this.employeeService.sortByFirstName(order);
	}
	
}
