package com.example.azaat.testfotth;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterfase {
    @GET("5a488f243000004c15c3c57e")
    Call<List<Person>> listResp();
}
