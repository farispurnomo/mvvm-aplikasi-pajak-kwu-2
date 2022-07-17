package com.example.tugas.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @Expose
    @SerializedName("email")
    var email: String,

    @Expose
    @SerializedName("password")
    var password: String,

    @Expose
    @SerializedName("confirmPassword")
    var confirmPassword: String,

    @Expose
    @SerializedName("kode")
    var kode: String,
)