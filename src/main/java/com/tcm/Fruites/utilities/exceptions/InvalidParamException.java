package com.tcm.Fruites.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Invalid Parameters")
public class InvalidParamException extends Exception{

	public InvalidParamException() {
		super();
	}
	
	public InvalidParamException(String message) {
		super();
	}
}
