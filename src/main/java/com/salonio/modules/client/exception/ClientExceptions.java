package com.salonio.modules.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ClientExceptions {

    private ClientExceptions() {

    }

    public static class ClientException extends RuntimeException {
        public ClientException(String message) {
            super(message);
        }

        public ClientException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ClientNotFoundException extends ClientExceptions.ClientException {
        public ClientNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class ClientConflictException extends ClientExceptions.ClientException {
        public ClientConflictException(String message) {
            super(message);
        }

        public ClientConflictException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class ClientDeletionException extends  ClientExceptions.ClientException {
        public ClientDeletionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
