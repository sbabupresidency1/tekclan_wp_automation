package com.tekclan.qa.exceptions;

public class TekclanReporterException
extends Exception
{
	private String message;

	public TekclanReporterException() {}

	public TekclanReporterException(String message)  {
		this.message = message;
	}

	public TekclanReporterException(String message, Throwable ex)  {
		super(message, ex);
		this.message = message;
	}
	public String toString()  {
		return "[Custom Reporter Exception] " + this.message;
	}
}
