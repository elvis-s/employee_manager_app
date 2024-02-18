package org.makerminds.javaweb.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.makerminds.javaweb.entity.Employee;
import org.makerminds.javaweb.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping(path="/{id}")
	ResponseEntity<?>createEmployee(@PathVariable Long id,  @RequestBody @Valid Employee employee, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for(FieldError fieldError : bindingResult.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);	
		}
		return ResponseEntity.ok(employeeService.createOrUpdateEmployee(employee, id));
	}
	@GetMapping(path="/{depId}/{empId}")
	Employee getEmployeeById( @PathVariable Long depId, @PathVariable Long empId) {
		return employeeService.getEmployee(depId, empId);	
	}
	@GetMapping(path="/all/{id}")
	public List<Employee>getEmployeeList(@PathVariable Long id){
		return employeeService.getEmployeeList(id);
	}
	@DeleteMapping(path="/delete/{departmentId}/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable Long departmentId, @PathVariable Long id){
		return employeeService.deleteById(departmentId,id);
	}
}
