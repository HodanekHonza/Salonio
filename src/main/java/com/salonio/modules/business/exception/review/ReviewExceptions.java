package com.salonio.modules.business.exception.review;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ReviewExceptions {

    private ReviewExceptions() {

    }

    public static class ReviewException extends RuntimeException {
        public ReviewException(String message) {
            super(message);
        }

        public ReviewException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ReviewNotFoundException extends ReviewExceptions.ReviewException {
        public ReviewNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class ReviewConflictException extends ReviewExceptions.ReviewException {
        public ReviewConflictException(String message) {
            super(message);
        }

        public ReviewConflictException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class ReviewDeletionException extends  ReviewExceptions.ReviewException {
        public ReviewDeletionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
