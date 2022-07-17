package com.example.tugas.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SendmailRequest(
    @Expose
    @SerializedName("email")
    var email: String,
)