package com.app.demologintask.interfaces

interface CommonResponseListener {
    fun onSuccessResult(obj: String,type:String)
    fun onErrorResult(msg: String)
}