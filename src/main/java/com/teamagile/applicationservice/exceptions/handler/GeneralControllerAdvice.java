package com.teamagile.applicationservice.exceptions.handler;

import com.teamagile.applicationservice.domain.response.common.ResponseStatus;
//import com.teamagile.applicationservice.exceptions.DataCreationException;
import com.teamagile.applicationservice.exceptions.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {
//    Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<ResponseStatus> handlerDataNotFound(RuntimeException e) {
        return new ResponseEntity(ResponseStatus.builder().is_success(false).message(e.getMessage()).build(), HttpStatus.OK);
    }
//
//    @ExceptionHandler(value = {TypeMismatchException.class, HttpMessageNotReadableException.class})
//    public ResponseEntity<ResponseStatus> handlerNumberFormatException(Exception e) {
//        return new ResponseEntity(ResponseStatus.builder()
//                .is_success(false)
//                .message("User Input Error. Please check on API documentation to make sure your request follow the desired pattern.")
//                .build(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    public ResponseEntity<ResponseStatus> handlerInvalidRequestBody(Exception e) {
//        return new ResponseEntity(ResponseStatus.builder()
//                .is_success(false)
//                .message("")
//                .build(),HttpStatus.BAD_REQUEST);
//}
}

