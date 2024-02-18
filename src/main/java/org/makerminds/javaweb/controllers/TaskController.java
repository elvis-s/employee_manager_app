package org.makerminds.javaweb.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.makerminds.javaweb.entity.Task;
import org.makerminds.javaweb.service.TaskService;
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
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}
	@PostMapping(path="/{departmentId}/{employeeId}")
	public ResponseEntity<?> addNewTask(@PathVariable Long departmentId, @PathVariable Long employeeId,@Valid @RequestBody Task newTask, BindingResult results){
		if(results.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for(FieldError fieldError : results.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);	
	}
		return new ResponseEntity<>(taskService.createNewTask(newTask, employeeId, departmentId), HttpStatus.CREATED);
	}
	
	@GetMapping(path="/{departmentId}/{employeeId}")
	public List<Task>getTaskList(@PathVariable Long departmentId, @PathVariable Long employeeId){
		return taskService.getTaskList(departmentId, employeeId);
	}
	
	@DeleteMapping(path="/delete/{departmentId}/{employeeId}/{taskId}")
	public ResponseEntity<?> deleteTask(@PathVariable Long departmentId, @PathVariable Long employeeId, @PathVariable Long taskId){
		return taskService.deleteTask(departmentId, employeeId, taskId);
	}
	@GetMapping(path="/{departmentId}/{employeeId}/{taskId}")
	public ResponseEntity<?> getTask(@PathVariable Long departmentId, @PathVariable Long employeeId, @PathVariable Long taskId){
		return new ResponseEntity<>(taskService.getTask(departmentId, employeeId, taskId),HttpStatus.OK);
	}
}
