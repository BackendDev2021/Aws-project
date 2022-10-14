package com.aws.api.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse<T> {

	private HttpStatus status;
	
	private Integer statusCode;
	
	private T data;
}
