package com.manager.parkinglot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingLotManagerDateUtil {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ParkingLotManagerDateUtil() {
    }

    public static LocalDateTime toLocalDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DEFAULT_FORMATTER);
    }
}
