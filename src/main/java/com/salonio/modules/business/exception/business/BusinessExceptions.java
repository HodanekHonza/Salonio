package com.salonio.modules.business.exception.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BusinessExceptions {

    private BusinessExceptions() {

    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }

        public BusinessException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class BusinessNotFoundException extends BusinessExceptions.BusinessException {
        public BusinessNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class BusinessConflictException extends BusinessExceptions.BusinessException {
        public BusinessConflictException(String message) {
            super(message);
        }

        public BusinessConflictException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class BusinessDeletionException extends  BusinessExceptions.BusinessException {
        public BusinessDeletionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
