package com.manager.parkinglot.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "PARKING_LOT_INFORMATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ParkingLotInfoEntity {

    @Id
    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "BUSINESS_TYPE", nullable = false)
    private String businessType;

    @Column(name = "TEL", nullable = false)
    private String tel;

    @Column(name = "TOTAL_PARKING_SLOT_COUNT")
    private long totalParkingSlotCount;

    @Column(name = "PARKING_CARS_COUNT")
    private long parkingCarsCount;

    @Column(name = "PAID_SERVICE")
    private String paidService;

    @Column(name = "NIGHT_FREE_OPEN")
    private String nightFreeOpen;

    @Column(name = "WEEKDAY_OPENING_TIME")
    private String weekdayOpeningTime;

    @Column(name = "WEEKDAY_CLOSING_TIME")
    private String weekdayClosingTime;

    @Column(name = "WEEKEND_OPENING_TIME")
    private String weekendOpeningTime;

    @Column(name = "WEEKEND_CLOSING_TIME")
    private String weekendClosingTime;

    @Column(name = "HOLIDAY_OPENING_TIME")
    private String holidayOpeningTime;

    @Column(name = "HOLIDAY_CLOSING_TIME")
    private String holidayClosingTime;

    @Column(name = "UPDATED_TIME")
    private LocalDateTime updatedTime;

    @Column(name = "SATURDAY_PAY_YN")
    private String saturdayPayYn;

    @Column(name = "HOLIDAY_PAY_YN")
    private String holidayPayYn;

    @Column(name = "MONTHLY_COMMUTER_PASS_PRICE")
    private String monthlyCommuterPassPrice;

    @Column(name = "BASIC_PARKING_FEE")
    private Double basicParkingFee;

    @Column(name = "BASIC_PARKING_MIN")
    private Double basicParkingMin;

    @Column(name = "ADDITIONAL_PARKING_FEE")
    private Double additionalParkingFee;

    @Column(name = "ADDITIONAL_PARKING_MIN")
    private Double additionalParkingMin;

    @Column(name = "BUS_BASIC_PARKING_FEE")
    private Double busParkingFee;

    @Column(name = "BUS_BASIC_PARKING_MIN")
    private Double busParkingMin;

    @Column(name = "BUS_ADDITIONAL_PARKING_FEE")
    private Double busAddRates;

    @Column(name = "BUS_ADDITIONAL_PARKING_MIN")
    private Double busAddTimeRate;

    @Column(name = "DAILY_LIMIT_CHARGE_AMOUNT")
    private Double dailyLimitChargeAmount;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;
}
