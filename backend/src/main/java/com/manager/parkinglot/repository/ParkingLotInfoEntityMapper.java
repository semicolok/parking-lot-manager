package com.manager.parkinglot.repository;

import com.manager.parkinglot.controller.dto.ParkingLotResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ParkingLotInfoEntityMapper {

    ParkingLotResponse toParkingLotResponse(ParkingLotInfoEntity source);

    @IterableMapping(elementTargetType = ParkingLotResponse.class)
    List<ParkingLotResponse> mapListToParkingLotResponseList(List<ParkingLotInfoEntity> source);
}
