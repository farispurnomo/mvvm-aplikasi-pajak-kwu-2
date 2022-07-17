package com.example.tugas.data.remote.response

import com.example.tugas.data.model.UserModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("status")
    val status: String,

    @Expose
    @SerializedName("msg")
    val msg: String,

    @Expose
    @SerializedName("data")
    val data: UserModel?
)