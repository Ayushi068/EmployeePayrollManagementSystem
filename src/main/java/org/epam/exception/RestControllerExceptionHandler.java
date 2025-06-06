package org.epam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest req)
    {
        List<String> inputErrors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(err->inputErrors.add(err.getDefaultMessage()));
        return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.name(),inputErrors.toString(), req.getDescription(false));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)

    public ExceptionResponse handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest req)
    {
        return new ExceptionResponse(new Date().toString(),HttpStatus.NOT_FOUND.name(),ex.getMessage(),req.getDescription(false));
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ExceptionResponse handleDepartmentNotFoundException(DepartmentNotFoundException ex, WebRequest req)
    {
        return new ExceptionResponse(new Date().toString(),HttpStatus.NOT_FOUND.name(),ex.getMessage(),req.getDescription(false));
    }

    @ExceptionHandler(DesignationNotFoundException.class)
    public ExceptionResponse handleDesignationNotFoundException(DesignationNotFoundException ex, WebRequest req)
    {
        return new ExceptionResponse(new Date().toString(),HttpStatus.NOT_FOUND.name(),ex.getMessage(),req.getDescription(false));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleIllegalArgumentException(IllegalArgumentException  ex, WebRequest req)
    {
        return new ExceptionResponse(new Date().toString(),HttpStatus.BAD_REQUEST.name(),ex.getMessage(),req.getDescription(false));
    }

}
