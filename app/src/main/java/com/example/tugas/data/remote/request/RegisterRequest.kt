package com.example.tugas.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @Expose
    @SerializedName("username")
    var userid: String,

    @Expose
    @SerializedName("password")
    var password: String

)