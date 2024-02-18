package org.makerminds.javaweb.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column; 
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="departments")
@Data
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "department_id")
	private Long id;
	@NotBlank(message= " The department is required")
	@Column(name= "department_name")
	private String name;
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "department")
	private List<Employee> employeeList;
	
	


	public Department() {
		
	}


	public Department(Long id, String name, List<Employee> employeeList) {
		this.id = id;
		this.name = name;
		this.employeeList = employeeList;
	}
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
		
	public List<Employee> getEmployeeList() {
		return employeeList;
	}


	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	
}
