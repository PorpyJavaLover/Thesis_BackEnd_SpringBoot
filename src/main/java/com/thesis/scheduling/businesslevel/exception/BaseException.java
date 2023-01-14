package com.thesis.scheduling.businesslevel.exception;

public abstract class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	public BaseException(String code) {
        super(code);
    }

}