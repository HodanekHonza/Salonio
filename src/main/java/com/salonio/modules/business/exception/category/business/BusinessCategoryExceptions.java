package com.salonio.modules.business.exception.category.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BusinessCategoryExceptions {
    private BusinessCategoryExceptions() {

    }

    public static class BusinessCategoryException extends RuntimeException {
        public BusinessCategoryException(String message) {
            super(message);
        }

        public BusinessCategoryException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class BusinessCategoryNotFoundException extends BusinessCategoryExceptions.BusinessCategoryException {
        public BusinessCategoryNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class BusinessCategoryConflictException extends BusinessCategoryExceptions.BusinessCategoryException {
        public BusinessCategoryConflictException(String message) {
            super(message);
        }

        public BusinessCategoryConflictException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class BusinessCategoryDeletionException extends  BusinessCategoryExceptions.BusinessCategoryException {
        public BusinessCategoryDeletionException(String message, Throwable cause) {
            super(message, cause);
        }
    }


}
