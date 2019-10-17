package com.manager.parkinglot.controller;

import com.manager.parkinglot.controller.dto.ParkingLotResponse;
import com.manager.parkinglot.repository.ParkingLotInfoEntity;
import com.manager.parkinglot.service.ParkingLotManager;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Parking Lot Management System")
@RestController
public class ParkingLotController {

    private final ParkingLotManager parkingLotManager;

    public ParkingLotController(ParkingLotManager parkingLotManager) {
        this.parkingLotManager = parkingLotManager;
    }

    @ApiOperation(value = "Search Parking Lot Information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "parking lot name", dataType = "string", paramType = "query", defaultValue = "", example = "도봉"),
            @ApiImplicitParam(name = "code", value = "parking lot code", dataType = "string", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "address", value = "parking lot address", dataType = "string", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "tel", value = "parking lot telephone number", dataType = "string", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "page", value = "page number", dataType = "int", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(name = "size", value = "number of items of that page", dataType = "int", paramType = "query", defaultValue = "20"),
            @ApiImplicitParam(name = "sort", value = "sorting", dataType = "string", paramType = "query", example = "basicParkingFee,asc"),
    })
    @GetMapping("/search")
    public ResponseEntity<Object> searchParkingLots(@QuerydslPredicate(root = ParkingLotInfoEntity.class) Predicate predicate, Pageable pageable) {
        final Page<ParkingLotResponse> parkingLotResponses = parkingLotManager.search(predicate, pageable);

        return new ResponseEntity<>(parkingLotResponses, HttpStatus.OK);
    }
}
