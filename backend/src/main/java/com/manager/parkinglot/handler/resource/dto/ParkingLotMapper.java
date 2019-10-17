package com.manager.parkinglot.handler.resource.dto;

import com.manager.parkinglot.repository.ParkingLotInfoEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ParkingLotMapper {

    ParkingLotInfoEntity toParkingLot(ParkingLot source);

    @IterableMapping(elementTargetType = ParkingLotInfoEntity.class)
    List<ParkingLotInfoEntity> mapListToList(List<ParkingLot> source);
}
