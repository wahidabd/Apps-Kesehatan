package com.udacoding.appskesehatan.data.network

import com.udacoding.appskesehatan.model.auth.RegisterResponse
import com.udacoding.appskesehatan.model.auth.UserResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {

    @FormUrlEncoded
    @POST("register.php")
    fun register(
            @Field("nama") nama: String,
            @Field("email") email: String,
            @Field("password") password: String
    ): Observable<RegisterResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun login(
            @Field("email") email: String,
            @Field("password") password: String
    ): Observable<UserResponse>
}