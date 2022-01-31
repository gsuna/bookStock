package com.gsuna.project.exception;

import java.text.MessageFormat;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;

	public BusinessException(String message) {
		super(message);
		this.message=message;
	}

	public BusinessException(String code, String message) {
		super(message);
		this.message=message;
		this.code = code;
	}
	public BusinessException(String code, String message,String ...params) {
		super(MessageFormat.format(message, params));
		String messageNew = MessageFormat.format(message, params);
		this.message=messageNew;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
