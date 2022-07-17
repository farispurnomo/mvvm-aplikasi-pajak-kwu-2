package com.example.tugas.data.model

data class RiwayatTagihanModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val tanggal: String,
    val total: Int = 0,
    val tgl_bayar: String? = ""
)