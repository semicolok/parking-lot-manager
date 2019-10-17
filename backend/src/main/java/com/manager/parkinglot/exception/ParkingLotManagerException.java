package com.manager.parkinglot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ParkingLotManagerException extends RuntimeException {
    private HttpStatus httpStatus;

    public ParkingLotManagerException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ParkingLotManagerException(String message, HttpStatus httpStatus, Throwable throwable) {
        super(message, throwable);
        this.httpStatus = httpStatus;
    }
}
