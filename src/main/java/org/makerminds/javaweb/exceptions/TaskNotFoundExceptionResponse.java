package org.makerminds.javaweb.exceptions;

public class TaskNotFoundExceptionResponse {
	
	private String TaskNotFoundMessage;

	public TaskNotFoundExceptionResponse(String TaskNotFoundMessage) {
		super();
		this.TaskNotFoundMessage = TaskNotFoundMessage;
	}

	public String getTaskNotFoundMessage() {
		return TaskNotFoundMessage;
	}

	public void setTaskNotFoundMessage(String TaskNotFoundMessage) {
		this.TaskNotFoundMessage = TaskNotFoundMessage;
	}
}
