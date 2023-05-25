package com.itbank.exception;

public class WrongAccessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public WrongAccessException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return msg;
	}

}
