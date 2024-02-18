package org.makerminds.javaweb.exceptions;

public class EmployeeNotFoundExceptionResponse {

	private String employeeNotFoundMessage;

	public EmployeeNotFoundExceptionResponse(String employeeNotFoundMessage) {
		super();
		this.employeeNotFoundMessage = employeeNotFoundMessage;
	}

	public String getEmployeenotFoundMessage() {
		return employeeNotFoundMessage;
	}

	public void setEmployeeNotFoundMessage(String employeeNotFoundMessage) {
		this.employeeNotFoundMessage = employeeNotFoundMessage;
	}
	
	
}
