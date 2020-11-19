package com.app.demologintask.network

import com.app.demologintask.constant.Constants
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


object WebServiceClient {
    private lateinit var interceptor: HttpLoggingInterceptor
    private lateinit var okHttpClient: OkHttpClient
    private var retrofit: Retrofit? = null


    val client: Retrofit
        get() {
            interceptor = HttpLoggingInterceptor()

            val loggingInterceptor = HttpLoggingInterceptor()

            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(Constants.SERVER_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build()
            }
            return retrofit!!

        }


}
