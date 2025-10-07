package com.salonio.modules.business.exception.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ServiceExceptions {

    private ServiceExceptions() {

    }

    public static class ServiceException extends RuntimeException {
        public ServiceException(String message) {
            super(message);
        }

        public ServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ServiceNotFoundException extends ServiceException {
        public ServiceNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class ServiceConflictException extends ServiceException {
        public ServiceConflictException(String message) {
            super(message);
        }

        public ServiceConflictException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class ServiceDeletionException extends ServiceException {
        public ServiceDeletionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
