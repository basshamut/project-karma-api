package com.karma.exceptions;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
    private String message;
    private Integer code;

	public ServiceException(String message, Integer code) {
        super();
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
