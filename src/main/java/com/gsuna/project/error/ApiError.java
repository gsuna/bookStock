package com.gsuna.project.error;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.gsuna.project.util.Views;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
	
	@JsonView(Views.Base.class)
	private String status;
	@JsonView(Views.Base.class)
	private String message;
	@JsonView(Views.Base.class)
	private String path;
	@JsonView(Views.Base.class)
	private long timestamp = new Date().getTime();
	
	public ApiError(String status, String message, String path) {
		this.status = status;
		this.message = message;
		this.path = path;
	}
	public ApiError() {
		
	}

	private Map<String,String> validationErrors ;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String, String> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(Map<String, String> validationErrors) {
		this.validationErrors = validationErrors;
	}	
	
	

}
