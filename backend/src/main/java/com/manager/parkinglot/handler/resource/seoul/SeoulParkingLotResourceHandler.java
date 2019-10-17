package com.manager.parkinglot.handler.resource.seoul;

import com.google.common.collect.Lists;
import com.manager.parkinglot.exception.ParkingLotManagerException;
import com.manager.parkinglot.handler.resource.ParkingLotResourceHandler;
import com.manager.parkinglot.handler.resource.dto.ParkingLot;
import com.manager.parkinglot.handler.resource.seoul.dto.GetParkInfo;
import com.manager.parkinglot.handler.resource.seoul.dto.SeoulParkingLot;
import com.manager.parkinglot.handler.resource.seoul.dto.SeoulParkingLotApiResponse;
import com.manager.parkinglot.handler.resource.seoul.dto.SeoulParkingLotMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SeoulParkingLotResourceHandler implements ParkingLotResourceHandler {

    private final SeoulParkingLotOpenApiRetrofitService seoulParkingLotOpenApiRetrofitService;
    private final SeoulParkingLotMapper seoulParkingLotMapper;
    private final String authKey;

    public SeoulParkingLotResourceHandler(SeoulParkingLotResourceConfig seoulParkingLotResourceConfig, SeoulParkingLotMapper seoulParkingLotMapper) {
        this.seoulParkingLotMapper = seoulParkingLotMapper;
        this.authKey = seoulParkingLotResourceConfig.getAuthKey();

        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(log::debug);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(seoulParkingLotResourceConfig.getConnectionTimeoutSeconds(), TimeUnit.SECONDS)
                .readTimeout(seoulParkingLotResourceConfig.getReadTimeoutSeconds(), TimeUnit.SECONDS)
                .writeTimeout(seoulParkingLotResourceConfig.getWriteTimeoutSeconds(), TimeUnit.SECONDS)
                .addNetworkInterceptor(loggingInterceptor)
                .build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(seoulParkingLotResourceConfig.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        this.seoulParkingLotOpenApiRetrofitService = retrofit.create(SeoulParkingLotOpenApiRetrofitService.class);
    }

    @Override
    public Page<ParkingLot> getAll(Pageable pageable) {
        try {
            final Response<SeoulParkingLotApiResponse> execute = seoulParkingLotOpenApiRetrofitService.getAll(authKey, pageable.getOffset() + 1, pageable.getOffset() + pageable.getPageSize()).execute();

            final GetParkInfo parkInfo = execute.body().getParkInfo();

            if (Objects.isNull(parkInfo)) {
                return new PageImpl<>(Lists.newArrayList(), pageable, 0);
            }

            final List<SeoulParkingLot> seoulParkingLots = parkInfo.getSeoulParkingLots();

            final List<ParkingLot> parkingLots = convertToParkingLots(seoulParkingLots);

            return new PageImpl<>(parkingLots, pageable, parkInfo.getTotalCount());
        } catch (IOException e) {
            throw new ParkingLotManagerException("Failed to get parkingLot information.", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    private List<ParkingLot> convertToParkingLots(List<SeoulParkingLot> seoulParkingLots) {
        if (CollectionUtils.isEmpty(seoulParkingLots)) {
            return Lists.newArrayList();
        }

        return seoulParkingLotMapper.mapListToList(seoulParkingLots);
    }
}
