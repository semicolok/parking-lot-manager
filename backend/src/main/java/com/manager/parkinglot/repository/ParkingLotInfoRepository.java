package com.manager.parkinglot.repository;

import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface ParkingLotInfoRepository extends JpaRepository<ParkingLotInfoEntity, String>, QuerydslPredicateExecutor<ParkingLotInfoEntity>, QuerydslBinderCustomizer<QParkingLotInfoEntity> {

    @Override
    default void customize(QuerydslBindings bindings, QParkingLotInfoEntity parkingLotInfoEntity) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.likeIgnoreCase("%" + value + "%"));
    }
}
