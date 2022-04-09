package com.greatlearning.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.employee.entity.Employee;
import com.greatlearning.employee.entity.Role;
import com.greatlearning.employee.entity.User;
import com.greatlearning.employee.repository.EmployeeRepository;
import com.greatlearning.employee.repository.RoleRepository;
import com.greatlearning.employee.repository.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;		
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();		
	}
	
	@Override
	public Employee save(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	@Override
	public Employee findEmployeeById(long empId) {
		return this.employeeRepository.findById(empId)
				.orElseThrow(()-> new IllegalArgumentException("Employee with the given id does not exists"));
	}
	
	@Override
	public Employee updateEmployee(Employee employee) {
		Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
		updatedEmployee.setFirstName(employee.getFirstName());
		updatedEmployee.setLastName(employee.getLastName());
		updatedEmployee.setEmail(employee.getEmail());
		return this.employeeRepository.save(updatedEmployee);
	}

	@Override
	public String deleteEmployeeById(long empId) {
		this.employeeRepository.deleteById(empId);
		return "Deleted Employee id -" + empId; 
	}

	@Override
	public List<Employee> fetchEmployeesByName(String firstName) {
		Employee employeesByName = new Employee();
		employeesByName.setFirstName(firstName);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("id","lastName","email");
		Example<Employee> example = Example.of(employeesByName, exampleMatcher);
		return this.employeeRepository.findAll(example);		
	}
	
	@Override
	public List<Employee> sortByFirstName(String order) {
		if (order.equals("asc")) {
			return employeeRepository.findAllByOrderByFirstNameAsc();
		}
		else {
			return employeeRepository.findAllByOrderByFirstNameDesc();
		}
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}
}
