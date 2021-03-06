package com.manager.parkinglot.exception;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class ParkingLotManagerExceptionHandler {

    @ExceptionHandler(ParkingLotManagerException.class)
    public ResponseEntity<Map<String, String>> handleParkingLotManagerException(ParkingLotManagerException e) {
        final HttpStatus httpStatus = e.getHttpStatus();
        final String message = e.getMessage();

        log.error("ParkingLotManagerException. httpStatus: {}, message: {}", httpStatus, message, e);

        final Map<String, String> responseBody = Maps.newHashMap();
        responseBody.put("errorMessage", message);

        return new ResponseEntity<>(responseBody, httpStatus);
    }
}
