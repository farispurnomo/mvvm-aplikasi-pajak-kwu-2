package com.example.tugas.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.Single

data class GeneralResponse<T>(
    @Expose
    @SerializedName("status")
    var status: String,

    @Expose
    @SerializedName("msg")
    var msg: String,

    @Expose
    @SerializedName("data")
    var data: T? = null
)