package com.gsuna.project.error;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


@RestController
public class ErrorHandler implements ErrorController {
	
	@Autowired
	private ErrorAttributes errorAttributes;
	
	//GSUNA
	@RequestMapping("/error")
	ApiError handleError(WebRequest webRequest) {
		Map<String,Object> attributes = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(Include.MESSAGE,Include.BINDING_ERRORS, Include.STACK_TRACE));
		String message= (String) attributes.get("message");
		String path= (String) attributes.get("path");
		int status= (Integer) attributes.get("status");
		ApiError error = new ApiError(status+"", message, path);
		return error;
	}
	

}
