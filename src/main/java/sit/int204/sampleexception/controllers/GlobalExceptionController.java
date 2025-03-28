package sit.int204.sampleexception.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sit.int204.sampleexception.exceptions.ItemNotFoundException;
import sit.int204.sampleexception.exceptions.MyErrorResponse;

@RestControllerAdvice

public class GlobalExceptionController {
    @Operation(hidden = false)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MyErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, HttpServletRequest request) {
        MyErrorResponse myErrorResponse = new MyErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myErrorResponse);
    }
    @Operation(hidden = false)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<MyErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex, HttpServletRequest request) {
        MyErrorResponse myErrorResponse = new MyErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myErrorResponse);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<MyErrorResponse> handleItemNotFoundException(
            ItemNotFoundException ex, HttpServletRequest request) {
        MyErrorResponse myErrorResponse = new MyErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(myErrorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        MyErrorResponse myErrorResponse = new MyErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value()
                , "Something Wrong, Please contact back-end support"
                , request.getRequestURI()
        );
        myErrorResponse.setStackTrace(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(myErrorResponse);
    }
}
