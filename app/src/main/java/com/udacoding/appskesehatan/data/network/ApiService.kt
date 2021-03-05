package com.udacoding.appskesehatan.data.network

import com.udacoding.appskesehatan.model.crud.ApiResponse
import com.udacoding.appskesehatan.model.crud.DataResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.util.*

interface ApiService {

    @GET("getData.php")
    fun getData(): Observable<DataResponse>

    @GET("getHistory.php")
    fun getHistory(
        @Query("user_id")user_id: Int
    ): Observable<DataResponse>

    @Multipart
    @POST("insert.php")
    fun insertData(
        @Part("user_id") user_id : Int?,
        @Part("judul") judul: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part file : MultipartBody.Part
    ): Single<ApiResponse>

    @Multipart
    @POST("update.php")
    fun update(
        @Part("id") id: Int?,
        @Part("user_id") user_id : Int?,
        @Part("judul") judul: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part file : MultipartBody.Part
    ): Single<ApiResponse>

    @FormUrlEncoded
    @POST("delete.php")
    fun delete(@Field("id")id: Int?): Observable<ApiResponse>
}