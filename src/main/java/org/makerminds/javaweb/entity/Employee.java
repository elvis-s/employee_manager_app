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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="employees")
@Data


public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column ( name="id" )
	private Long id;
	@NotBlank (message= "Name is required")
	@Column ( name="name", nullable=false )
	private String name;
	@NotBlank (message= "Address is required")
	@Column ( name="address", nullable=false )
	private String address;
	@NotBlank (message= "Email is required")
	@Email(message="Invalid email format")
	@Column ( name="email", nullable=false )
	private String email;
	@NotBlank (message= "Phone Number is required")
	@Size(min = 12, max=15, message="Invalid phone number")
	@Column ( name="phoneNumber", nullable=false )
	private String phoneNumber;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="departmentId", nullable= false, updatable= false)
	private Department department;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonIgnore
	private List<Task> taskList;
	
	public Employee() {
	}

	public Employee(Long id, String name, String address, String email, String phoneNumber, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.department = department;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
}

