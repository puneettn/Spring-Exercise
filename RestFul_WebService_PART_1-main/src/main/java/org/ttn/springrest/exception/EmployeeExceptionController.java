package org.ttn.springrest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.ttn.springrest.entity.Employee;

import java.time.LocalDateTime;

@ControllerAdvice
public class EmployeeExceptionController extends ResponseEntityExceptionHandler {

   @ExceptionHandler(Exception.class)
   public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
      ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), exception.getMessage(),
              request.getDescription(false));

      return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(EmployeeNotfoundException.class)
   public final ResponseEntity<Object> handleUserNotFoundException(EmployeeNotfoundException exception, WebRequest request) {
      ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), exception.getMessage(),
              request.getDescription(false));

      return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
   }

   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
      ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Validation Failed", exception.getBindingResult().toString());
      return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
   }



}