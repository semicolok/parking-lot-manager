package com.manager.parkinglot.handler.resource.seoul;

import com.manager.parkinglot.handler.resource.seoul.dto.SeoulParkingLotApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SeoulParkingLotOpenApiRetrofitService {

    @GET("/{authKey}/json/GetParkInfo/{startIndex}/{endIndex}")
    Call<SeoulParkingLotApiResponse> getAll(@Path("authKey") String authKey, @Path("startIndex") long startIndex, @Path("endIndex") long endIndex);
}
