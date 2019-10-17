package com.manager.parkinglot.handler.resource.seoul.dto;

import com.manager.parkinglot.handler.resource.dto.ParkingLot;
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
class SeoulParkingLotMapperTest {

    SeoulParkingLotMapper seoulParkingLotMapper;

    @BeforeEach
    void init() {
        seoulParkingLotMapper = Mappers.getMapper(SeoulParkingLotMapper.class);
    }

    @Test
    void toParkingLot() {
        SeoulParkingLot seoulParkingLot = mock(SeoulParkingLot.class);

        when(seoulParkingLot.getCode()).thenReturn(RandomStringUtils.randomAlphanumeric(10));
        when(seoulParkingLot.getName()).thenReturn(RandomStringUtils.randomAlphanumeric(10));
        when(seoulParkingLot.getAddress()).thenReturn(RandomStringUtils.randomAlphanumeric(10));
        when(seoulParkingLot.getType()).thenReturn(RandomStringUtils.randomAlphanumeric(2));
        when(seoulParkingLot.getTel()).thenReturn(RandomStringUtils.randomAlphanumeric(20));
        when(seoulParkingLot.getWeekdayOpeningTime()).thenReturn(RandomStringUtils.randomNumeric(4));
        when(seoulParkingLot.getWeekdayClosingTime()).thenReturn(RandomStringUtils.randomNumeric(4));
        when(seoulParkingLot.getWeekendOpeningTime()).thenReturn(RandomStringUtils.randomNumeric(4));
        when(seoulParkingLot.getWeekendClosingTime()).thenReturn(RandomStringUtils.randomNumeric(4));
        when(seoulParkingLot.getUpdatedTime()).thenReturn("2019-11-21 15:30:45");

        final ParkingLot parkingLot = seoulParkingLotMapper.toParkingLot(seoulParkingLot);

        assertThat(parkingLot, allOf(
                hasProperty("code", is(seoulParkingLot.getCode())),
                hasProperty("name", is(seoulParkingLot.getName())),
                hasProperty("address", is(seoulParkingLot.getAddress())),
                hasProperty("type", is(seoulParkingLot.getType())),
                hasProperty("tel", is(seoulParkingLot.getTel())),
                hasProperty("weekdayOpeningTime", is(seoulParkingLot.getWeekdayOpeningTime())),
                hasProperty("weekdayClosingTime", is(seoulParkingLot.getWeekdayClosingTime())),
                hasProperty("weekendOpeningTime", is(seoulParkingLot.getWeekendOpeningTime())),
                hasProperty("weekendClosingTime", is(seoulParkingLot.getWeekendClosingTime()))
        ));
    }
}