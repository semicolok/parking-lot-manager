package com.manager.parkinglot.handler.resource.seoul.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeoulParkingLot {

    @JsonProperty("PARKING_CODE")
    private String code;

    @JsonProperty("PARKING_NAME")
    private String name;

    @JsonProperty("ADDR")
    private String address;

    @JsonProperty("PARKING_TYPE")
    private String type;

    @JsonProperty("PARKING_TYPE_NM")
    private String typeName;

    @JsonProperty("OPERATION_RULE")
    private String businessType;

    @JsonProperty("OPERATION_RULE_NM")
    private String businessTypeDescription;

    @JsonProperty("TEL")
    private String tel;

    @JsonProperty("QUE_STATUS")
    private String queStatus;

    @JsonProperty("QUE_STATUS_NM")
    private String queStatusName;

    @JsonProperty("CAPACITY")
    private long totalParkingSlotCount;

    @JsonProperty("CUR_PARKING")
    private long parkingCarsCount;

    @JsonProperty("CUR_PARKING_TIME")
    private String parkingCountUpdateTime;

    @JsonProperty("PAY_YN")
    private String paidService;

    @JsonProperty("PAY_NM")
    private String paidServiceDescription;

    @JsonProperty("NIGHT_FREE_OPEN")
    private String nightFreeOpen;

    @JsonProperty("NIGHT_FREE_OPEN_NM")
    private String nightFreeOpenNm;

    @JsonProperty("WEEKDAY_BEGIN_TIME")
    private String weekdayOpeningTime;

    @JsonProperty("WEEKDAY_END_TIME")
    private String weekdayClosingTime;

    @JsonProperty("WEEKEND_BEGIN_TIME")
    private String weekendOpeningTime;

    @JsonProperty("WEEKEND_END_TIME")
    private String weekendClosingTime;

    @JsonProperty("HOLIDAY_BEGIN_TIME")
    private String holidayOpeningTime;

    @JsonProperty("HOLIDAY_END_TIME")
    private String holidayClosingTime;

    @JsonProperty("SYNC_TIME")
    private String updatedTime;

    @JsonProperty("SATURDAY_PAY_YN")
    private String saturdayPayYn;

    @JsonProperty("SATURDAY_PAY_NM")
    private String saturdayPayNm;

    @JsonProperty("HOLIDAY_PAY_YN")
    private String holidayPayYn;

    @JsonProperty("HOLIDAY_PAY_NM")
    private String holidayPayNm;

    @JsonProperty("FULLTIME_MONTHLY")
    private String monthlyCommuterPassPrice;

    @JsonProperty("GRP_PARKNM")
    private String grpParkNm;

    @JsonProperty("RATES")
    private Double basicParkingFee;

    @JsonProperty("TIME_RATE")
    private Double basicParkingMin;

    @JsonProperty("ADD_RATES")
    private Double additionalParkingFee;

    @JsonProperty("ADD_TIME_RATE")
    private Double additionalParkingMin;

    @JsonProperty("BUS_RATES")
    private Double busParkingFee;

    @JsonProperty("BUS_TIME_RATE")
    private Double busParkingMin;

    @JsonProperty("BUS_ADD_RATES")
    private Double busAddRates;

    @JsonProperty("BUS_ADD_TIME_RATE")
    private Double busAddTimeRate;

    @JsonProperty("DAY_MAXIMUM")
    private Double dailyLimitChargeAmount;

    @JsonProperty("LAT")
    private Double latitude;

    @JsonProperty("LNG")
    private Double longitude;

    @JsonProperty("ASSIGN_CODE")
    private String assignCode;

    @JsonProperty("ASSIGN_CODE_NM")
    private String assignCodeNm;
}
