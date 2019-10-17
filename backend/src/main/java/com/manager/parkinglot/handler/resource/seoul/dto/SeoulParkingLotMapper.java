package com.manager.parkinglot.handler.resource.seoul.dto;

import com.manager.parkinglot.handler.resource.dto.ParkingLot;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SeoulParkingLotMapper {

    @Mappings({
            @Mapping(target = "updatedTime", expression = "java(com.manager.parkinglot.util.ParkingLotManagerDateUtil.toLocalDateTime(source.getUpdatedTime()))")
    })
    ParkingLot toParkingLot(SeoulParkingLot source);

    @IterableMapping(elementTargetType = ParkingLot.class)
    List<ParkingLot> mapListToList(List<SeoulParkingLot> source);
}
