package com.example.tugas.data.repository

import com.example.tugas.data.local.db.DatabaseService
import com.example.tugas.data.local.prefs.UserPreferences
import com.example.tugas.data.model.RiwayatTagihanModel
import com.example.tugas.data.model.TagihanModel
import com.example.tugas.data.remote.NetworkService
import com.example.tugas.data.remote.request.GetTagihanRequest
import com.example.tugas.data.remote.response.GeneralResponse
import com.example.tugas.data.remote.response.LoginResponse
import com.example.tugas.utils.common.Resource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TagihanRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {
    fun doGetTagihan(): Single<GeneralResponse<List<RiwayatTagihanModel>>> {
        val nop =userPreferences.getusernop();
        return networkService.doGetTagihanlistCall(GetTagihanRequest(nop))
//        RiwayatTagihanModel
//        return Single.just(
//            GeneralResponse(
//                "200", "sukses",
//                listOf(
//                    RiwayatTagihanModel(
//                        "1",
//                        "Pajak PBB",
//                        "34.68.908.370.589.3961.1",
//                        "15 April 2022",
//                        500000
//                    ),
//                    RiwayatTagihanModel(
//                        "2",
//                        "Pajak PBB",
//                        "34.68.908.370.589.3961.1",
//                        "15 April 2022",
//                        500000
//                    )
//                )
//            )
//        )
    }


    fun doGetTagihanAktif(): Single<GeneralResponse<TagihanModel>> {
//        val tagihan = TagihanModel(
//            3000000,
//            listOf(
//                RiwayatTagihanModel(
//                    "1",
//                    "Pajak PBB",
//                    "54.88.871.370.890.3261.0",
//                    "25 Agustus 2022",
//                    6000000
//                ),
//                RiwayatTagihanModel(
//                    "2",
//                    "Pajak PBB",
//                    "26.35.404.573.782.4962.9",
//                    "10 Mei 2022",
//                    5000000
//                )
//            )
//        )
//        return Single.just(GeneralResponse("200", "sukses", tagihan))
        val nop =userPreferences.getusernop();
        return networkService.doGetTagihanaktifCall(GetTagihanRequest(nop))
    }

    fun doGetRiwayatTagihan(): Single<GeneralResponse<List<RiwayatTagihanModel>>> {
//        val riwayats = listOf(
//            RiwayatTagihanModel(
//                "1",
//                "Pajak PBB",
//                "64.68.628.671.689.3241.6",
//                "25 Juni 2022",
//                90000
//            ),
//            RiwayatTagihanModel(
//                "2",
//                "Pajak PBB",
//                "24.11.123.990.689.2200.6",
//                "01 Juli 2022",
//                200000
//            )
//        )
//        return Single.just(GeneralResponse("200", "sukses", riwayats))
        val nop =userPreferences.getusernop();
        return networkService.doGetHistoryTagihanCall(GetTagihanRequest(nop))
    }

    fun doGetDetailTagihan(): Single<GeneralResponse<RiwayatTagihanModel>>{
        return Single.just(
            GeneralResponse(
                "200", "sukses",
            )
        )
    }
}