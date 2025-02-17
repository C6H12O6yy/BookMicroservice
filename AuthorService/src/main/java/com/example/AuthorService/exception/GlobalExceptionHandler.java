package com.example.AuthorService.exception;

import com.example.AuthorService.configs.Translator;
import com.example.AuthorService.utils.Constants;
import com.example.AuthorService.utils.MessagesConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for handling various exceptions across the application.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Exception handler for DataAccessException.
     *
     * @param exception the exception that occurred
     * @param webRequest the current web request
     * @return ResponseEntity containing ErrorDetails
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorDetails> handleDataAccessException(DataAccessException exception,
                                                                  WebRequest webRequest) {
        log.error(Constants.LOG_DATA_ACCESS_EXCEPTION, exception.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(),  exception.getMessage(),
        webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for general Exception.
     *
     * @param exception the exception that occurred
     * @param webRequest the current web request
     * @return ResponseEntity containing ErrorDetails
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
                                                              WebRequest webRequest) {
        log.error(Constants.LOG_EXCEPTION, exception.getMessage());                                        
        ErrorDetails errorDetails = new ErrorDetails(new Date(),  exception.getMessage(),
               webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for MethodArgumentNotValidException (Validation errors).
     *
     * @param ex the MethodArgumentNotValidException
     * @param request the current web request
     * @return ResponseEntity containing ErrorDetails
     */
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                     WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
        .map(error -> Translator.toLocale(error.getDefaultMessage()))
        .collect(Collectors.toList());                                                            
        ErrorDetails errorDetails = new ErrorDetails(new Date(), Translator.toLocale(MessagesConstants.VALIDATION_FAILED_MESSAGE), errorMessages.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

        /**
     * Exception handler to handle ResourceNotFoundException.
     *
     * @param exception   The ResourceNotFoundException to handle.
     * @param webRequest  The current web request.
     * @return ResponseEntity containing an ErrorDetails object and HTTP status 404 (Not Found).
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest) {
        log.error(Constants.LOG_RESOURCE_NOT_FOUND_EXCEPTION, exception.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
