package br.com.banco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    public ClienteServiceException(String message) {
        super(message);
    }

    public ClienteServiceException(Exception exception) {
        super(exception);
    }
}
