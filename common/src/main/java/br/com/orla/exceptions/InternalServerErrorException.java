package br.com.orla.exceptions;

public class InternalServerErrorException extends RuntimeException {

    private String message;

    public InternalServerErrorException(String message) {
        super(message);
    }
}
