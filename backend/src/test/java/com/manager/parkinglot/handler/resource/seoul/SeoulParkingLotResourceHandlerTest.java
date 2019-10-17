package com.manager.parkinglot.handler.resource.seoul;

import com.manager.parkinglot.handler.resource.seoul.dto.GetParkInfo;
import com.manager.parkinglot.handler.resource.seoul.dto.SeoulParkingLotApiResponse;
import com.manager.parkinglot.handler.resource.seoul.dto.SeoulParkingLotMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SeoulParkingLotResourceHandlerTest {

    SeoulParkingLotResourceHandler seoulParkingLotResourceHandler;

    @Mock
    SeoulParkingLotResourceConfig seoulParkingLotResourceConfig;

    @Mock
    SeoulParkingLotOpenApiRetrofitService seoulParkingLotOpenApiRetrofitService;

    @Mock
    SeoulParkingLotMapper seoulParkingLotMapper;

    @BeforeEach
    void init() {
        when(seoulParkingLotResourceConfig.getBaseUrl()).thenReturn("http://localhost:8081");
        when(seoulParkingLotResourceConfig.getAuthKey()).thenReturn("testAuthKey");

        seoulParkingLotResourceHandler = new SeoulParkingLotResourceHandler(seoulParkingLotResourceConfig, seoulParkingLotMapper);

        ReflectionTestUtils.setField(seoulParkingLotResourceHandler, "seoulParkingLotOpenApiRetrofitService", seoulParkingLotOpenApiRetrofitService);
    }

    @Test
    void getAll() throws Exception {
        Call call = mock(Call.class);
        Response response = mock(Response.class);
        SeoulParkingLotApiResponse seoulParkingLotApiResponse = mock(SeoulParkingLotApiResponse.class);
        GetParkInfo parkInfo = mock(GetParkInfo.class);

        when(seoulParkingLotApiResponse.getParkInfo()).thenReturn(parkInfo);
        when(response.body()).thenReturn(seoulParkingLotApiResponse);
        when(call.execute()).thenReturn(response);

        when(seoulParkingLotOpenApiRetrofitService.getAll(anyString(), anyLong(), anyLong())).thenReturn(call);

        Pageable pageable = mock(Pageable.class);
        seoulParkingLotResourceHandler.getAll(pageable);

        verify(seoulParkingLotMapper, times(0)).toParkingLot(any());
    }
}