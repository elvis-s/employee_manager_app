package org.makerminds.javaweb.exceptions;

public class DepartmentNotFoundExceptionResponse {

	private String departmentNotFoundMessage;

	public DepartmentNotFoundExceptionResponse(String departmentNotFoundMessage) {
		this.departmentNotFoundMessage = departmentNotFoundMessage;
	}

	public String getDepartmentNotFoundMessage() {
		return departmentNotFoundMessage;
	}

	public void setDepartmentNotFoundMessage(String departmentNotFoundMessage) {
		this.departmentNotFoundMessage = departmentNotFoundMessage;
	}

	
	
}
