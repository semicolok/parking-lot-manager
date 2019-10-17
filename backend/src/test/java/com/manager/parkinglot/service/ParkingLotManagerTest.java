package com.manager.parkinglot.service;

import com.google.common.collect.Lists;
import com.manager.parkinglot.repository.ParkingLotInfoEntity;
import com.manager.parkinglot.repository.ParkingLotInfoEntityMapper;
import com.manager.parkinglot.repository.ParkingLotInfoRepository;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParkingLotManagerTest {

    ParkingLotManager parkingLotManager;

    @Mock
    ParkingLotInfoRepository parkingLotInfoRepository;

    @Mock
    ParkingLotInfoEntityMapper parkingLotInfoEntityMapper;

    @BeforeEach
    void init() {
        parkingLotManager = new ParkingLotManager(parkingLotInfoRepository, parkingLotInfoEntityMapper);
    }

    Stream<Arguments> searchParameterProvider() {
        return Stream.of(
                arguments(null, mock(Pageable.class), Lists.newArrayList(new ParkingLotInfoEntity()), 1),
                arguments(null, mock(Pageable.class), null, 0),
                arguments(mock(Predicate.class), mock(Pageable.class), Lists.newArrayList(new ParkingLotInfoEntity()), 1),
                arguments(mock(Predicate.class), mock(Pageable.class), null, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("searchParameterProvider")
    void search(Predicate predicate, Pageable pageable, List<ParkingLotInfoEntity> parkingLotInfoEntities, int mapperInvokeCount) {
        Page<ParkingLotInfoEntity> parkingLots = mock(Page.class);

        when(parkingLots.getContent()).thenReturn(parkingLotInfoEntities);

        if (Objects.isNull(predicate)) {
            when(parkingLotInfoRepository.findAll(eq(pageable))).thenReturn(parkingLots);

            parkingLotManager.search(predicate, pageable);

            verify(parkingLotInfoRepository, times(1)).findAll(eq(pageable));
            verify(parkingLotInfoRepository, times(0)).findAll(any(Predicate.class), eq(pageable));
            verify(parkingLotInfoEntityMapper, times(mapperInvokeCount)).mapListToParkingLotResponseList(anyList());
        } else {
            when(parkingLotInfoRepository.findAll(any(Predicate.class), eq(pageable))).thenReturn(parkingLots);

            parkingLotManager.search(predicate, pageable);

            verify(parkingLotInfoRepository, times(0)).findAll(eq(pageable));
            verify(parkingLotInfoRepository, times(1)).findAll(any(Predicate.class), eq(pageable));
            verify(parkingLotInfoEntityMapper, times(mapperInvokeCount)).mapListToParkingLotResponseList(anyList());
        }
    }
}