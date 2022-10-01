package com.booking.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourseNotFoundException extends RuntimeException{
	
	String resourceName;
	
	String fieldName;
	
	long fieldValue;

	public ResourseNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not fount with %s: %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	
	
	

}
