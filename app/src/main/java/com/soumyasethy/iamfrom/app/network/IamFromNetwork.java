package com.soumyasethy.iamfrom.app.network;

import com.soumyasethy.iamfrom.activities.login.mvp.model.Credentials;
import com.soumyasethy.iamfrom.app.network.model.Details;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IamFromNetwork {

    @Headers({"Content-type: application/json", "timezone: Europe/London"})
    @POST("user/v1/login")
    Observable<Details> loginUser(@Body Credentials credentials);

    @Headers("timezone: Europe/London")
    @GET("/user/v1/details")
    Observable<Details> getUserDetails(@Query("email") String username);


}
