package com.manager.parkinglot.handler.resource;

import com.manager.parkinglot.handler.resource.dto.ParkingLot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParkingLotResourceHandler {
    Page<ParkingLot> getAll(Pageable pageable);
}
