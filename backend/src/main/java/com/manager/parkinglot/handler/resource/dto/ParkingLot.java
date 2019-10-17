package com.manager.parkinglot.handler.resource.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingLot {
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
}
