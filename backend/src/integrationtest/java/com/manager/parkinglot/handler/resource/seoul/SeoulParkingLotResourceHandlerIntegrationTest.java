package com.manager.parkinglot.handler.resource.seoul;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manager.parkinglot.handler.resource.dto.ParkingLot;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SeoulParkingLotResourceHandlerIntegrationTest {

    @Autowired
    SeoulParkingLotResourceHandler seoulParkingLotResourceHandler;

    @Disabled
    @Test
    void getAll() throws Exception {
        final Pageable pageable = PageRequest.of(0, 10);
        final Page<ParkingLot> parkingLots = seoulParkingLotResourceHandler.getAll(pageable);

        final ObjectMapper objectMapper = new ObjectMapper();
        log.info("json result: {}", objectMapper.writeValueAsString(parkingLots));

        final List<ParkingLot> content = parkingLots.getContent();

        assertThat(content.size(), is(10));
    }

}