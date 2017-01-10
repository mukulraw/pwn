package com.example.mukul.pwn1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface interfaces{

    @GET("feedback/getrating.php")
    Call<rateBean> getRating();

    @Multipart
    @POST("feedback/rate.php")
    Call<String> setRating(@Part("req") String req);


    @Multipart
    @POST("feedback/add_complaint.php")
    Call<String> setComplaint(@Part("req") String req);


}
