package com.tekclan.qa.exceptions;

public class TekclanReporterStepFailedException
extends RuntimeException {

	public TekclanReporterStepFailedException() {}

	public TekclanReporterStepFailedException(String paramString) {}

	public String toString()  {
		return "[Custom Reporter Step Failed Exception]";
	}
}

