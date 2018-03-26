package com.sps.friends.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * handle API exceptions
     * @param apiException
     * @return ResponseEntity<?>
     */
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<?> handleAppException(ApiException apiException) {
        if(apiException != null) {
            logger.error(apiException.getMessage(), apiException);
        }
        return ResponseEntity.badRequest().body(apiException.getMessage());
    }

    /**
     *  handle all uncatched Exception
     * @param exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleUncatchException(Exception exception) {
        logger.error("Internal Server Exception thrown :", exception);
    }
}
