package com.manager.parkinglot.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class ParkingLotManagerDateUtilTest {

    @Test
    void toLocalDateTime() {
        final LocalDateTime localDateTime = ParkingLotManagerDateUtil.toLocalDateTime("2019-11-21 15:30:45");

        assertThat(localDateTime.getYear(), is(2019));
        assertThat(localDateTime.getMonthValue(), is(11));
        assertThat(localDateTime.getDayOfMonth(), is(21));
        assertThat(localDateTime.getHour(), is(15));
        assertThat(localDateTime.getMinute(), is(30));
        assertThat(localDateTime.getSecond(), is(45));
    }
}