package br.com.orla.exceptions;

public class BusinessException extends RuntimeException {

    private String message;

    public BusinessException(String message) {
        super(message);
    }
}
