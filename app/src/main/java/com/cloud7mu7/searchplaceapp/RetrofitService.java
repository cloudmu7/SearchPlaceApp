package com.cloud7mu7.searchplaceapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitService {

    @Headers("Authorization: KakaoAK fb6d7b7508595f811e4c58cbb3d02fc7")
    @GET("/v2/local/search/keyword.json")
    Call<String> searchPlaceByString(@Query("query") String query, @Query("x") String longitude, @Query("y") String latitude);

    @Headers("Authorization: KakaoAK fb6d7b7508595f811e4c58cbb3d02fc7")
    @GET("/v2/local/search/keyword.json")
    Call<SearchLocalApiResponse> searchPlace(@Query("query") String query, @Query("x") String longitude, @Query("y") String latitude);

}
