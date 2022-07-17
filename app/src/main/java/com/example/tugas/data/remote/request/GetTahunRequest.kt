package com.example.tugas.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetTahunRequest(
    @Expose
    @SerializedName("idbilling")
    var idbilling: String,

)