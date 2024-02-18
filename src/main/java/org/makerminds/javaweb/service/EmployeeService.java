package org.makerminds.javaweb.service;

import java.util.List;

import org.makerminds.javaweb.entity.Department;
import org.makerminds.javaweb.entity.Employee;
import org.makerminds.javaweb.exceptions.EmployeeNotFoundException;
import org.makerminds.javaweb.repositories.DepartmentRepository;
import org.makerminds.javaweb.repositories.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final DepartmentService departmentService;

	public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, DepartmentService departmentService) {
	this.employeeRepository = employeeRepository;
	this.departmentRepository = departmentRepository;
	this.departmentService = departmentService;
	}
	
	public Employee createOrUpdateEmployee (Employee employee,Long departmentId) {
		employee.setDepartment(departmentService.findById(departmentId));
		return employeeRepository.save(employee);
	}
	public Employee findById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}
	public List<Employee> getEmployeeList(Long departmentId){
		return departmentService.findById(departmentId).getEmployeeList();
	}
	public ResponseEntity<?>deleteById(Long departmentId, Long id){
	Employee employee = findById(id);
	if(employee != null) {
		if(employee.getDepartment().getId()==departmentId) {
			
//		Department department = departmentRepository.findById(departmentId).get();
//		department.getEmployeeList().remove(employee);
//		departmentRepository.save(department);
			employeeRepository.delete(employee);
		String message= "Employee with id " + employee.getId() + " has been deleted";
		return ResponseEntity.ok().body(message);
		}else {
			String message = "Employee was not found";
			throw new EmployeeNotFoundException(message);
	}
}
		else {
			String message = "Employee was not found";
			throw new EmployeeNotFoundException(message);
	}
	}
	public Employee getEmployee(Long departmentId, Long employeeId) {
		Employee employee = findById(employeeId);
		if(employee == null || employee.getDepartment().getId() != departmentId) {
			throw new EmployeeNotFoundException("Employee was not found");
		}
		return employee;
	}
}
