package com.example.mukul.pwn1;

import retrofit2.Call;
import retrofit2.http.GET;


public interface interfaces{

    @GET("feedback/getrating.php")
    Call<rateBean> getRating();


}
