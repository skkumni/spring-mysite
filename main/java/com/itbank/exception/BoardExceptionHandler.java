package com.itbank.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BoardExceptionHandler {

	@ExceptionHandler(WrongAccessException.class)
	public ModelAndView wrongAccess(WrongAccessException e) {
		
		ModelAndView mav = new ModelAndView("alert");
		mav.addObject("msg", e.toString());
		return mav;
	}
}
