package com.restful.service.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
	private Date timestamp;	
	private String msg;
	private String details;
	private HttpStatus exceptionStatus;
	public ExceptionResponse(Date timestamp, String msg, String details, HttpStatus internalServerError) {
		super();
		this.timestamp = timestamp;
		this.msg = msg;
		this.details = details;
		this.exceptionStatus = internalServerError;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMsg() {
		return msg;
	}
	public String getDetails() {
		return details;
	}
	public HttpStatus getExceptionStatus() {
		return exceptionStatus;
	}
}
