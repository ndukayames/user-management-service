package com.example.usermanagementservice.exceptions;



import com.example.usermanagementservice.dtos.responses.BaseResponse;
import com.example.usermanagementservice.dtos.responses.ResponseWithData;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(AuthenticationException.class)
//    public final ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
//        ResponseModel customErrorResponse = new ResponseModel(false, ex.getMessage());
//        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.UNAUTHORIZED);
//    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();
    
        List<String> errors = ex.getBindingResult()
                                  .getFieldErrors()
                                  .stream()
                                  .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                  .collect(Collectors.toList());
    
        body.put("errors", errors);
        BaseResponse customErrorResponse = new ResponseWithData<>(false, errors);
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        BaseResponse customErrorResponse = new BaseResponse(false, ex.getMessage());
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PasswordMismatchException.class)
    public final ResponseEntity<Object> handlePasswordMismatchException(PasswordMismatchException ex, WebRequest request) {
        BaseResponse customErrorResponse = new BaseResponse(false, ex.getMessage());
        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }
@ExceptionHandler(UnknownServerErrorException.class)
public final ResponseEntity<Object> handleUnknownServerErrorException(UnknownServerErrorException ex, WebRequest request) {
    BaseResponse customErrorResponse = new BaseResponse(false, ex.getMessage());
    return new ResponseEntity<Object>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
}

//    @ExceptionHandler(BadCredentialsException.class)
//    public final ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
//        ResponseModel customErrorResponse = new ResponseModel(false, ex.getMessage());
//        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.UNAUTHORIZED);
//    }
//    @ExceptionHandler(NoSuchElementException.class)
//    public final ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
//        ResponseModel customErrorResponse = new ResponseModel(false, ex.getMessage());
//        return new ResponseEntity<Object>(customErrorResponse, HttpStatus.BAD_REQUEST);
//    }
}
