package com.salonio.modules.business.exception.category.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ServiceCategoryExceptions {

    private ServiceCategoryExceptions() {

    }

    public static class ServiceCategoryException extends RuntimeException {
        public ServiceCategoryException(String message) {
            super(message);
        }

        public ServiceCategoryException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ServiceCategoryNotFoundException extends ServiceCategoryExceptions.ServiceCategoryException {
        public ServiceCategoryNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class ServiceCategoryConflictException extends ServiceCategoryExceptions.ServiceCategoryException {
        public ServiceCategoryConflictException(String message) {
            super(message);
        }

        public ServiceCategoryConflictException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class ServiceCategoryDeletionException extends  ServiceCategoryExceptions.ServiceCategoryException {
        public ServiceCategoryDeletionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
