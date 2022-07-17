package com.example.tugas.data.model

data class DetailNopModel(
    val nop: String,
    val nama_wp: String,
    val lokasi: String,
    val provinsi: String,
    val kota: String,
    val kecamatan: String,
    val kelurahan: String,
    val luas_tanah: String,
    val luas_bangunan: String,
    val tahun_pajak: String,
    val jatuh_tempo: String,
    val pajak_pokok: Int,
    val denda: Int,
    val biaya_admin: Int,
    val total_pembayaran: Int
)