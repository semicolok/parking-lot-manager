package com.manager.parkinglot.repository;

import com.manager.parkinglot.controller.dto.ParkingLotResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParkingLotInfoEntityMapperTest {

    ParkingLotInfoEntityMapper parkingLotInfoEntityMapper;

    @BeforeEach
    void init() {
        parkingLotInfoEntityMapper = Mappers.getMapper(ParkingLotInfoEntityMapper.class);
    }

    @Test
    void toParkingLotResponse() {
        ParkingLotInfoEntity parkingLotInfoEntity = mock(ParkingLotInfoEntity.class);

        when(parkingLotInfoEntity.getCode()).thenReturn(RandomStringUtils.randomAlphanumeric(10));
        when(parkingLotInfoEntity.getName()).thenReturn(RandomStringUtils.randomAlphanumeric(10));
        when(parkingLotInfoEntity.getAddress()).thenReturn(RandomStringUtils.randomAlphanumeric(10));
        when(parkingLotInfoEntity.getType()).thenReturn(RandomStringUtils.randomAlphanumeric(2));
        when(parkingLotInfoEntity.getTel()).thenReturn(RandomStringUtils.randomAlphanumeric(20));
        when(parkingLotInfoEntity.getWeekdayOpeningTime()).thenReturn(RandomStringUtils.randomNumeric(4));
        when(parkingLotInfoEntity.getWeekdayClosingTime()).thenReturn(RandomStringUtils.randomNumeric(4));
        when(parkingLotInfoEntity.getWeekendOpeningTime()).thenReturn(RandomStringUtils.randomNumeric(4));
        when(parkingLotInfoEntity.getWeekendClosingTime()).thenReturn(RandomStringUtils.randomNumeric(4));

        final ParkingLotResponse parkingLotResponse = parkingLotInfoEntityMapper.toParkingLotResponse(parkingLotInfoEntity);

        assertThat(parkingLotResponse, allOf(
                hasProperty("code", is(parkingLotInfoEntity.getCode())),
                hasProperty("name", is(parkingLotInfoEntity.getName())),
                hasProperty("address", is(parkingLotInfoEntity.getAddress())),
                hasProperty("type", is(parkingLotInfoEntity.getType())),
                hasProperty("tel", is(parkingLotInfoEntity.getTel())),
                hasProperty("weekdayOpeningTime", is(parkingLotInfoEntity.getWeekdayOpeningTime())),
                hasProperty("weekdayClosingTime", is(parkingLotInfoEntity.getWeekdayClosingTime())),
                hasProperty("weekendOpeningTime", is(parkingLotInfoEntity.getWeekendOpeningTime())),
                hasProperty("weekendClosingTime", is(parkingLotInfoEntity.getWeekendClosingTime()))
        ));
    }
}