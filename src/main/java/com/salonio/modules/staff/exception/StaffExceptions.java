package com.salonio.modules.staff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class StaffExceptions {

    private StaffExceptions() {

    }

    public static class StaffException extends RuntimeException {
        public StaffException(String message) {
            super(message);
        }

        public StaffException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class StaffNotFoundException extends StaffExceptions.StaffException {
        public StaffNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class StaffConflictException extends StaffExceptions.StaffException {
        public StaffConflictException(String message) {
            super(message);
        }

        public StaffConflictException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class StaffDeletionException extends  StaffExceptions.StaffException {
        public StaffDeletionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
