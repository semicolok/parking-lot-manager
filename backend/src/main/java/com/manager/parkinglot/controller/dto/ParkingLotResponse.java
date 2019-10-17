package com.manager.parkinglot.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingLotResponse {
    private String code;
    private String name;
    private String address;
    private String type;
    private String businessType;
    private String tel;
    private long totalParkingSlotCount;
    private long parkingCarsCount;
    private String parkingCountUpdateTime;
    private String paidService;
    private String nightFreeOpen;
    private String weekdayOpeningTime;
    private String weekdayClosingTime;
    private String weekendOpeningTime;
    private String weekendClosingTime;
    private String holidayOpeningTime;
    private String holidayClosingTime;
    private LocalDateTime updatedTime;
    private String saturdayPayYn;
    private String holidayPayYn;
    private String monthlyCommuterPassPrice;
    private Double basicParkingFee;
    private Double basicParkingMin;
    private Double additionalParkingFee;
    private Double additionalParkingMin;
    private Double busParkingFee;
    private Double busParkingMin;
    private Double busAddTimeRate;
    private Double busAddRates;
    private Double dailyLimitChargeAmount;
    private Double latitude;
    private Double longitude;

    public boolean isAvailableNow() {
        final LocalDateTime now = LocalDateTime.now();

        final int nowTimeNumber = now.getHour() * 100 + now.getMinute();

        int openingTimeNumber;
        int closingTimeNumber;

        try {
            if (now.getDayOfWeek().getValue() > 5) {
                openingTimeNumber = Integer.parseInt(this.weekendOpeningTime);
                closingTimeNumber = Integer.parseInt(this.weekendClosingTime);
            } else {
                openingTimeNumber = Integer.parseInt(this.weekdayOpeningTime);
                closingTimeNumber = Integer.parseInt(this.weekdayClosingTime);
            }

            if (closingTimeNumber == 0) {
                closingTimeNumber = 2400;
            }

            if (nowTimeNumber > openingTimeNumber && nowTimeNumber < closingTimeNumber) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            return true;
        }

        return false;
    }
}
