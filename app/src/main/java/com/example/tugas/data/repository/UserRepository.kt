package com.example.tugas.data.repository

import com.example.tugas.data.local.db.DatabaseService
import com.example.tugas.data.local.prefs.UserPreferences
import com.example.tugas.data.model.NotificationModel
import com.example.tugas.data.model.UserModel
import com.example.tugas.data.remote.NetworkService
import com.example.tugas.data.remote.request.*
import com.example.tugas.data.remote.response.GeneralResponse
import com.example.tugas.data.remote.response.LoginResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {

    fun saveCurrentUser(userModel: UserModel) {
        userPreferences.setId(userModel.id)
        userPreferences.setUserId(userModel.user_id)
        userPreferences.setUserName(userModel.user_name)
//        userPreferences.setUserFoto(userModel.urlImage)
        userPreferences.setIsDaftar(userModel.isDaftar)
        userPreferences.setUserEmail(userModel.user_email)
        userPreferences.setusernop(userModel.nop)
    }

    fun removeCurrentUser() {
        userPreferences.removeId()
        userPreferences.removeUserId()
        userPreferences.removeUserName()
        userPreferences.removeUserFoto()
        userPreferences.removeIsDaftar()
        userPreferences.removeUserEmail()
        userPreferences.removeusernop()
    }

    fun getCurrentUser(): UserModel? {
        val id = userPreferences.getId()
        val userId = userPreferences.getUserId()
        val userName = userPreferences.getUserName() ?: ""
        val userFoto = userPreferences.getUserFoto() ?: ""
        val userEmail = userPreferences.getUserEmail() ?: ""
        val isDaftar = userPreferences.getIsDaftar() ?: false
        val userNop = userPreferences.getusernop() ?: ""

        return if (id !== null && userId != null)
            UserModel(
                id = id,
                user_id = userId,
                user_name = userName,
//                urlImage = userFoto,
                user_email = userEmail,
                isDaftar = isDaftar,
                nop =userNop
            )
        else
            null
    }

    fun doUserLogin(email: String, password: String): Single<LoginResponse> {
        return networkService.doLoginCall(LoginRequest(email,password))
//        UserModel
//        return Single.just(
//            LoginResponse(
//                "200", "sukses",
//                UserModel(
//                    "123",
//                    "test",
//                    "test",
//                    "test@gmail.com",
//                    false
//                )
//            )
//        )
    }

    fun doUserRegister(
        email: String,
        password: String,
        confirmPassword: String): Single<LoginResponse> {
        return networkService.doRegisterCall(RegisterRequest(email,password))
    }
//    fun doUserRegister(
//        email: String,
//        password: String,
//        confirmPassword: String
//    ): Single<GeneralResponse<String>> {
//        return Single.just(
//            GeneralResponse(
//                "200",
//                "sukses",
//                ""
//            )
//        )
//    }

    fun doUserForgetPassword(email: String): Single<LoginResponse> {
        return networkService.dosendemailCall(SendmailRequest(email))
//        return Single.just(
//            GeneralResponse(
//                "200",
//                "sukses",
//                ""
//            )
//        )
    }

    fun doUserUpdatePassword(
        email: String,
        password: String,
        confirmPassword: String,
        kode: String
    ): Single<LoginResponse> {
        return networkService.doResetPasswordCall(ResetPasswordRequest(email,password,confirmPassword,kode))
//        return Single.just(
//            GeneralResponse(
//                "200",
//                "sukses",
//                ""
//            )
//        )
    }

    fun doGetListNotifikasi(): Single<LoginResponse> {
//        return Single.just(
//            GeneralResponse(
//                "200", "sukses",
//                listOf(
//                    NotificationModel(
//                        "1",
//                        "Pajak Pbb Baru",
//                        "nop xx.xx.xxx.xxx.xxxx.x pajak tahun 2022",
//                        "01-01-2022 00:01"
//                    )
//                )
//            )
//        )
        val nop =userPreferences.getusernop();
        return networkService.doGetListNotifikasi(GetTagihanRequest(nop))
    }
}