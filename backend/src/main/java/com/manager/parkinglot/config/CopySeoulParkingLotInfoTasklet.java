package com.manager.parkinglot.config;

import com.manager.parkinglot.handler.resource.ParkingLotResourceHandler;
import com.manager.parkinglot.handler.resource.dto.ParkingLot;
import com.manager.parkinglot.handler.resource.dto.ParkingLotMapper;
import com.manager.parkinglot.repository.ParkingLotInfoEntity;
import com.manager.parkinglot.repository.ParkingLotInfoRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class CopySeoulParkingLotInfoTasklet implements Tasklet {

    @Autowired
    @Qualifier("seoulParkingLotResourceHandler")
    private ParkingLotResourceHandler parkingLotResourceHandler;

    @Autowired
    private ParkingLotInfoRepository parkingLotInfoRepository;

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        int pageNumber = 0;
        boolean finished = false;

        while (true) {
            if (finished) {
                break;
            }

            final Page<ParkingLot> parkingLots = parkingLotResourceHandler.getAll(PageRequest.of(pageNumber++, 1000));
            final List<ParkingLot> content = parkingLots.getContent();

            final List<ParkingLotInfoEntity> parkingLotInfoEntities = parkingLotMapper.mapListToList(content);

            parkingLotInfoEntities.forEach(parkingLotInfoEntity -> {
                parkingLotInfoRepository.findById(parkingLotInfoEntity.getCode());
            });

            if (!CollectionUtils.isEmpty(parkingLotInfoEntities)) {
                parkingLotInfoRepository.saveAll(parkingLotInfoEntities);
            }

            finished = parkingLots.isLast();
        }

        return RepeatStatus.FINISHED;
    }
}
