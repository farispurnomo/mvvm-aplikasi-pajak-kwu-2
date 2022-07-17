package com.example.tugas.data.repository

import com.example.tugas.R
import com.example.tugas.data.local.db.DatabaseService
import com.example.tugas.data.local.prefs.UserPreferences
import com.example.tugas.data.model.BannerModel
import com.example.tugas.data.model.MenuPembayaranModel
import com.example.tugas.data.remote.NetworkService
import com.example.tugas.data.remote.response.GeneralResponse
import com.example.tugas.data.remote.response.LoginResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommonRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {

    fun doGetTotalSaldo(): Single<GeneralResponse<Int>> {
        val saldo = (100000 until 10000000).random()
        return Single.just(
            GeneralResponse("200", "sukses", saldo)
        )
    }

    fun doGetBanner(): Single<GeneralResponse<List<BannerModel>>> {
//        val bannerList = listOf(
//            BannerModel(1, "https://picsum.photos/id/1043/5184/3456"),
//            BannerModel(2, "https://picsum.photos/id/1039/6945/4635"),
//            BannerModel(3, "https://picsum.photos/id/1038/3914/5863"),
//            BannerModel(4, "https://picsum.photos/id/1037/5760/3840")
//        )
//        return Single.just(GeneralResponse("200", "sukses", bannerList))
//        dobackgroundhomeCall
        return networkService.dobackgroundhomeCall()
    }

    fun doGetMenuPembayaran(): Single<GeneralResponse<List<MenuPembayaranModel>>> {
        val menus = listOf(
            MenuPembayaranModel(
                "1",
                "Pajak PBB",
                "",
                "pbb",
                R.drawable.ic_pajak_pbb
            ),
            MenuPembayaranModel(
                "2",
                "PLN",
                "",
                "pln",
                R.drawable.ic_pln
            ),
            MenuPembayaranModel(
                "3",
                "PDAM",
                "",
                "pdam",
                R.drawable.ic_pdam
            ),
            MenuPembayaranModel(
                "4",
                "Pulsa",
                "",
                "pulsa",
                R.drawable.ic_pulsa
            ),
            MenuPembayaranModel(
                "5",
                "Paket Data",
                "",
                "paketData",
                R.drawable.ic_data
            )
        )
        return Single.just(GeneralResponse("200", "sukses", menus))
    }
}