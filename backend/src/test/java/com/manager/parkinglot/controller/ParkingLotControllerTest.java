package com.manager.parkinglot.controller;

import com.google.common.collect.Lists;
import com.manager.parkinglot.controller.dto.ParkingLotResponse;
import com.manager.parkinglot.service.ParkingLotManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ParkingLotController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParkingLotControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParkingLotManager parkingLotManager;

    @Test
    void searchParkingLots() throws Exception {
        List<ParkingLotResponse> parkingLotResponses = Lists.newArrayList();

        ParkingLotResponse firstParkingLot = new ParkingLotResponse();
        parkingLotResponses.add(firstParkingLot);
        firstParkingLot.setCode(generateAnyString());
        firstParkingLot.setName(generateAnyString());
        firstParkingLot.setAddress(generateAnyString());

        ParkingLotResponse secondParkingLot = new ParkingLotResponse();
        parkingLotResponses.add(secondParkingLot);
        secondParkingLot.setCode(generateAnyString());
        secondParkingLot.setName(generateAnyString());
        secondParkingLot.setAddress(generateAnyString());

        when(parkingLotManager.search(any(), any())).thenReturn(new PageImpl<>(parkingLotResponses, PageRequest.of(0, 10), 2));

        mockMvc.perform(get("/search").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.content[0].code", is(firstParkingLot.getCode())))
                .andExpect(jsonPath("$.content[0].name", is(firstParkingLot.getName())))
                .andExpect(jsonPath("$.content[0].address", is(firstParkingLot.getAddress())))
                .andExpect(jsonPath("$.content[1].code", is(secondParkingLot.getCode())))
                .andExpect(jsonPath("$.content[1].name", is(secondParkingLot.getName())))
                .andExpect(jsonPath("$.content[1].address", is(secondParkingLot.getAddress())));

    }

    private String generateAnyString() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}