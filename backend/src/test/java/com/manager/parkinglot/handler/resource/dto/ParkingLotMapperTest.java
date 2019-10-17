package com.manager.parkinglot.handler.resource.dto;

import com.manager.parkinglot.repository.ParkingLotInfoEntity;
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
class ParkingLotMapperTest {

    ParkingLotMapper parkingLotMapper;

    @BeforeEach
    void init() {
        parkingLotMapper = Mappers.getMapper(ParkingLotMapper.class);
    }

    @Test
    void toParkingLot() {
        ParkingLot parkingLot = mock(ParkingLot.class);

        when(parkingLot.getCode()).thenReturn(RandomStringUtils.randomAlphanumeric(10));
        when(parkingLot.getName()).thenReturn(RandomStringUtils.randomAlphanumeric(10));
        when(parkingLot.getAddress()).thenReturn(RandomStringUtils.randomAlphanumeric(10));
        when(parkingLot.getType()).thenReturn(RandomStringUtils.randomAlphanumeric(2));
        when(parkingLot.getTel()).thenReturn(RandomStringUtils.randomAlphanumeric(20));
        when(parkingLot.getWeekdayOpeningTime()).thenReturn(RandomStringUtils.randomNumeric(4));
        when(parkingLot.getWeekdayClosingTime()).thenReturn(RandomStringUtils.randomNumeric(4));
        when(parkingLot.getWeekendOpeningTime()).thenReturn(RandomStringUtils.randomNumeric(4));
        when(parkingLot.getWeekendClosingTime()).thenReturn(RandomStringUtils.randomNumeric(4));

        final ParkingLotInfoEntity parkingLotInfoEntity = parkingLotMapper.toParkingLot(parkingLot);

        assertThat(parkingLotInfoEntity, allOf(
                hasProperty("code", is(parkingLot.getCode())),
                hasProperty("name", is(parkingLot.getName())),
                hasProperty("address", is(parkingLot.getAddress())),
                hasProperty("type", is(parkingLot.getType())),
                hasProperty("tel", is(parkingLot.getTel())),
                hasProperty("weekdayOpeningTime", is(parkingLot.getWeekdayOpeningTime())),
                hasProperty("weekdayClosingTime", is(parkingLot.getWeekdayClosingTime())),
                hasProperty("weekendOpeningTime", is(parkingLot.getWeekendOpeningTime())),
                hasProperty("weekendClosingTime", is(parkingLot.getWeekendClosingTime()))
        ));
    }
}