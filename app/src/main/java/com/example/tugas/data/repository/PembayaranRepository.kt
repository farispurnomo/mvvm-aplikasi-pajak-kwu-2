package com.example.tugas.data.repository

import com.example.tugas.data.local.db.DatabaseService
import com.example.tugas.data.local.prefs.UserPreferences
import com.example.tugas.data.model.DetailNopModel
import com.example.tugas.data.remote.NetworkService
import com.example.tugas.data.remote.request.GetPembayaranRequest
import com.example.tugas.data.remote.request.GetTahunRequest
import com.example.tugas.data.remote.request.LoginRequest
import com.example.tugas.data.remote.response.GeneralResponse
import com.example.tugas.data.remote.response.LoginResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PembayaranRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {

    fun doGetPBBTahunTagihanByNop(nop: String): Single<GeneralResponse<List<String>>> {
//        return networkService.doGetTahunCall(GetTahunRequest(nop))
        return Single.just(
            GeneralResponse(
                "200",
                "sukses",
                listOf(
                    "2022", "2021", "2020"
                )
            )
        )
    }

    fun doGetPBBDetailPembayaran(
        nop: String,
        tahun: String
    ): Single<GeneralResponse<DetailNopModel>> {
//        return networkService.doGetPembayaranCall(GetPembayaranRequest(nop,tahun))
//        DetailNopModel
        return Single.just(
            GeneralResponse(
                "200",
                "sukses",
                DetailNopModel(
                    nop,
                    "Dian",
                    "Bojong Soang",
                    "Banten",
                    "Kab. Lebak",
                    "Maja",
                    "Pasir Kembang",
                    "84",
                    "36",
                    tahun,
                    "30-02-2022",
                    360000,
                    50000,
                    5000,
                    305000
                )
            )
        )
    }

    fun doGetPBBBayarTagihan(id: String): Single<GeneralResponse<String>> {
        return Single.just(
            GeneralResponse(
                "200",
                "sukses",
                ""
            )
        )
    }
}