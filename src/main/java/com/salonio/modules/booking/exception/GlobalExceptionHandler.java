package com.salonio.modules.booking.exception;

import com.salonio.modules.booking.api.dto.BookingErrorResponse;
//import com.salonio.modules.booking.exception.DuplicateBookingException; // Your custom exception
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ConcurrentModificationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle EntityNotFoundException (e.g., from getBooking, updateBooking, deleteBooking)
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
//            EntityNotFoundException ex, HttpServletRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(),
//                HttpStatus.NOT_FOUND.getReasonPhrase(),
//                ex.getMessage(),
//                request.getRequestURI()
//        );
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }

//    // Handle DuplicateBookingException (e.g., from createBooking)
//    @ExceptionHandler(DuplicateBookingException.class)
//    public ResponseEntity<ErrorResponse> handleDuplicateBookingException(
//            DuplicateBookingException ex, HttpServletRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.CONFLICT.value(), // 409 Conflict
//                HttpStatus.CONFLICT.getReasonPhrase(),
//                ex.getMessage(),
//                request.getRequestURI()
//        )
;
//        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
//    }

    // Handle ConcurrentModificationException (e.g., from updateBooking)
    @ExceptionHandler(ConcurrentModificationException.class)
    public ResponseEntity<BookingErrorResponse> handleConcurrentModificationException(
            ConcurrentModificationException ex, HttpServletRequest request) {
        BookingErrorResponse bookingErrorResponse = new BookingErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(bookingErrorResponse, HttpStatus.CONFLICT);
    }

    // You might also want a generic handler for unexpected RuntimeExceptions
    // This catches anything not specifically handled above
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BookingErrorResponse> handleGenericRuntimeException(
            RuntimeException ex, HttpServletRequest request) {
        // Log the exception for debugging purposes, as it's unexpected
//         logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);

        BookingErrorResponse bookingErrorResponse = new BookingErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected error occurred. Please try again later.", // Generic message for client
                request.getRequestURI()
        );
        return new ResponseEntity<>(bookingErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // also add handlers for common Spring exceptions like
    // MethodArgumentNotValidException (for @Valid failures on DTOs)
    // HttpMessageNotReadableException (for malformed JSON)
}