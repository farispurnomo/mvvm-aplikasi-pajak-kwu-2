package com.example.tugas.data.remote.response

import com.example.tugas.data.model.UserModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @Expose
    @SerializedName("status")
    val status: String,

    @Expose
    @SerializedName("msg")
    val msg: String,

)