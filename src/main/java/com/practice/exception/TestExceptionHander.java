package com.practice.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.practice.enums.PracExceptionEnum;
import com.practice.response.ErrorResponse;

@ControllerAdvice
public class TestExceptionHander {

	@ExceptionHandler(TestException.class)
	public ErrorResponse handleTestException(TestException e) {
		System.out.println("----> TestException handled");
		return new ErrorResponse(PracExceptionEnum.TESTENUM, e.getMessage());
	}

	@ExceptionHandler(ArithmeticException.class)
	public ErrorResponse handleArithmeticException(ArithmeticException e) {
		return new ErrorResponse(PracExceptionEnum.TESTENUM, e.getMessage());
	}

}
