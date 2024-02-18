package org.makerminds.javaweb.service;

import java.lang.annotation.Retention;
import java.util.List;

import org.makerminds.javaweb.entity.Employee;
import org.makerminds.javaweb.entity.Task;
import org.makerminds.javaweb.exceptions.TaskNotFoundException;
import org.makerminds.javaweb.repositories.EmployeeRepository;
import org.makerminds.javaweb.repositories.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final EmployeeService employeeService;
	
	public TaskService(TaskRepository taskRepository,EmployeeService employeeService) {
		super();
		this.taskRepository = taskRepository;
		this.employeeService= employeeService;
	}
	
	public Task createNewTask(Task newTask, Long employeeId, Long departmentId) {
		Employee employee = employeeService.getEmployee(departmentId, employeeId);
		newTask.setEmployee(employee);
		if(newTask.getId() == null)
		newTask.setStatus("INPUT QUEUE");
		return taskRepository.save(newTask);
	} 
	
	public List<Task> getTaskList(Long departmentID, Long employeeID) {
		return employeeService.getEmployee(departmentID, employeeID).getTaskList();
	}
	
	public ResponseEntity<?> deleteTask(Long departmentId, Long employeeId, Long taskId) {
		taskRepository.delete(getTask(departmentId, employeeId, taskId));
		return ResponseEntity.ok().body("Task with id " +taskId + " was deleted");
		
		
	}
	public Task getTask(Long departmentId, Long employeeId, Long taskId) {
		Employee employee = employeeService.getEmployee(departmentId, employeeId);
		Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task was not found"));
		if(task.getEmployee().getId()==employeeId) {
			return task;
		}
		throw new TaskNotFoundException("Task not found");
	}
}











