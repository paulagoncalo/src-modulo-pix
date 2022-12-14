package br.com.banco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ChavePixException extends Exception {
    private static final long serialVersionUID = 1L;

    public ChavePixException(String message) {
        super(message);
    }
}
