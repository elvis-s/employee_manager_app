package org.makerminds.javaweb.entity;

import jakarta.annotation.Priority;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Description is required")
	// @Max(value= 256)
	private String description;
	@NotBlank(message = "Acceptance criteria is required")
	//@Max(value= 256)
	private String acceptanceCriteria;
	//@NotBlank(message = "Status is required")
	private String status;
	//@NotBlank(message = "Priority is required")
	@Positive(message= "Prioritty should be a possitive number 1-3")
	@Min(value=1, message="value should be more than one")
	@Max(value=3)
	private int priority;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="employeeId", updatable = false, nullable= false)
	private Employee employee;
	
	public Task() {
		
	}
	public Task(Long id, String description, String acceptanceCriteria, String status, int priority,
			Employee employee) {
		super();
		this.id=id;
		this.description = description;
		this.acceptanceCriteria = acceptanceCriteria;
		this.status = status;
		this.priority = priority;
		this.employee = employee;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAcceptanceCriteria() {
		return acceptanceCriteria;
	}
	public void setAcceptanceCriteria(String acceptanceCriteria) {
		this.acceptanceCriteria = acceptanceCriteria;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
