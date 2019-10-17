package com.manager.parkinglot.service;

import com.google.common.collect.Lists;
import com.manager.parkinglot.controller.dto.ParkingLotResponse;
import com.manager.parkinglot.repository.ParkingLotInfoEntity;
import com.manager.parkinglot.repository.ParkingLotInfoEntityMapper;
import com.manager.parkinglot.repository.ParkingLotInfoRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ParkingLotManager {

    private final ParkingLotInfoRepository parkingLotInfoRepository;
    private final ParkingLotInfoEntityMapper parkingLotInfoEntityMapper;

    public ParkingLotManager(ParkingLotInfoRepository parkingLotInfoRepository, ParkingLotInfoEntityMapper parkingLotInfoEntityMapper) {
        this.parkingLotInfoRepository = parkingLotInfoRepository;
        this.parkingLotInfoEntityMapper = parkingLotInfoEntityMapper;
    }

    public Page<ParkingLotResponse> search(Predicate predicate, Pageable pageable) {
        Page<ParkingLotInfoEntity> parkingLots;

        if (Objects.isNull(predicate)) {
            parkingLots = parkingLotInfoRepository.findAll(pageable);
        } else {
            parkingLots = parkingLotInfoRepository.findAll(predicate, pageable);
        }

        return new PageImpl<>(convertToParkingLotResponse(parkingLots.getContent()), pageable, parkingLots.getTotalElements());
    }

    private List<ParkingLotResponse> convertToParkingLotResponse(List<ParkingLotInfoEntity> parkingLotInfoEntities) {
        if (CollectionUtils.isEmpty(parkingLotInfoEntities)) {
            return Lists.newArrayList();
        }

        return parkingLotInfoEntityMapper.mapListToParkingLotResponseList(parkingLotInfoEntities);
    }
}
