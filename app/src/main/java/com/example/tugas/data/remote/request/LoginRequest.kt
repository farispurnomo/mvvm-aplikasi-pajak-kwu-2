package com.example.tugas.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @Expose
    @SerializedName("username")
    var userid: String,

    @Expose
    @SerializedName("password")
    var password: String
)