package com.api.users.exceptions;
import com.api.users.exceptions.response.ErrorResponse;
import  com.api.users.exceptions.response.ErrorResponse.ErrorDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse fieldErrors(HttpServletRequest request, MethodArgumentNotValidException ex) {

        // Errores
        List<ObjectError> errors = ex.getAllErrors();

        // Detalles
        List<ErrorResponse.ErrorDetails> errorDetails = new ArrayList<>();

        for ( ObjectError error : errors) {
            ErrorResponse.ErrorDetails detail = new ErrorDetails();
            detail.setFieldName(((FieldError) error).getField());
            detail.setMessage(error.getDefaultMessage());
            errorDetails.add(detail);
        }

        ErrorResponse response = new ErrorResponse(ex, request.getRequestURI(), errorDetails);

        return response;
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse fieldErrors(HttpServletRequest request, BindException ex) {

        // Errores
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        // Detalles
        List<ErrorDetails> errorDetails = new ArrayList<>();

        for ( FieldError error : errors) {
            ErrorResponse.ErrorDetails detail = new ErrorResponse.ErrorDetails();
            detail.setFieldName(error.getField());
            detail.setMessage(error.getDefaultMessage());
            errorDetails.add(detail);
        }

        ErrorResponse response = new ErrorResponse(ex, request.getRequestURI(), errorDetails);

        return response;
    }



    @ExceptionHandler({
            BadRequestException.class,
            ServletException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            MissingServletRequestPartException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

}
