package com.app.demologintask.network

import com.google.gson.JsonObject
import io.reactivex.Single
import com.app.demologintask.login.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface BackEndApi {

    @POST("api/login")
    fun Login(@HeaderMap headers: Map<String, String>, @Body body: JsonObject): Call<LoginResponse>

}

