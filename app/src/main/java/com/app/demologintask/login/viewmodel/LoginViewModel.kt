package com.app.demologintask.login.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.demologintask.login.LoginResponse
import com.app.demologintask.network.BackEndApi
import com.app.demologintask.network.WebServiceClient
import com.app.demologintask.util.SingleLiveEvent
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application),
    Callback<LoginResponse> {


    var btnSelected: ObservableBoolean? = null
    var username: ObservableField<String>? = null
    var password: ObservableField<String>? = null
    var progressDialog: SingleLiveEvent<Boolean>? = null
    var userLogin: MutableLiveData<LoginResponse>? = null

    init {
        btnSelected = ObservableBoolean(true)
        progressDialog = SingleLiveEvent<Boolean>()
        username = ObservableField("")
        password = ObservableField("")
        userLogin = MutableLiveData<LoginResponse>()
    }


    fun login() {

        progressDialog?.value = true

        val header: LinkedHashMap<String, String> = LinkedHashMap()
        header["Content-Type"] = "application/x-www-form-urlencoded"
        header["IMEI"] = "510110406068589"
        header["IMSI"] = "357175048449937"

        val jObj = JSONObject()
        jObj.put("username", username?.get()!!)
        jObj.put("password", password?.get()!!)

        val cjObj = Gson().fromJson(
            jObj.toString(),
            JsonObject::class.java
        )

        WebServiceClient.client.create(BackEndApi::class.java).Login(header, cjObj).enqueue(this)

    }

    override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
        progressDialog?.value = false
        userLogin?.value = response?.body()

    }

    override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
        progressDialog?.value = false

    }
}