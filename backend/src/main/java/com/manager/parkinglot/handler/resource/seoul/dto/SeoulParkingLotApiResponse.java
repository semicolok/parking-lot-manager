package com.manager.parkinglot.handler.resource.seoul.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeoulParkingLotApiResponse {

    @JsonProperty("GetParkInfo")
    private GetParkInfo parkInfo;
}
