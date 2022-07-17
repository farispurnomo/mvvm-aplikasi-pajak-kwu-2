package com.example.tugas.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetPembayaranRequest(

    @Expose
    @SerializedName("idbilling")
    var idbilling: String,

    @Expose
    @SerializedName("tahun")
    var tahun: String,

)