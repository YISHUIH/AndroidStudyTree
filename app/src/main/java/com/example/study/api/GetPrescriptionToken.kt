package com.example.study.api

import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Date: 2019/11/13 9:51    <br>
 * Description: 获取处方token   <br>
 */
interface GetPrescriptionToken {
    //https://cflz.nj12320.org/pbminterface/
    @POST("hos/authentication.json")
    fun request(@Body requestBody: RequestBody,@Header("Content-Length") length:Long ): Call<JsonObject>
}