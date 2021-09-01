package dev.guptaakshay.exception;

public class LoanServiceException extends RuntimeException{

    public LoanServiceException() {
        super();
    }

    public LoanServiceException(String message) {
        super(message);
    }
}
