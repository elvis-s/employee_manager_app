package org.makerminds.javaweb.service;

import java.util.List;  

import org.makerminds.javaweb.entity.Department;
import org.makerminds.javaweb.exceptions.DepartmentNotFoundException;
import org.makerminds.javaweb.repositories.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;






@Service

public class DepartmentService {

	private final DepartmentRepository departmentRepository;


	public DepartmentService(DepartmentRepository departmentRepository) {
			this.departmentRepository = departmentRepository;
	}

	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}
	
	public ResponseEntity<?>deleteDepartmentById(Long id){
		Department department = departmentRepository.findById(id).orElseThrow(()->
		new DepartmentNotFoundException("Department not found"));
		departmentRepository.delete(department);
		String message = "Department with id"+ id + " has been deleted";
		return ResponseEntity.ok().body(message);
		
		
	}
	public List<Department> getDepartments(){
		
		return departmentRepository.findAll();
		
	}
	public Department findById(Long id) {
		Department department= departmentRepository.findById(id).orElseThrow(()->
		new DepartmentNotFoundException("Department not found"));
		return department;
	}
}
