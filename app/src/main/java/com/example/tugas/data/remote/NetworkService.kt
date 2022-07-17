package com.example.tugas.data.remote

import com.example.tugas.data.model.BannerModel
import com.example.tugas.data.model.RiwayatTagihanModel
import com.example.tugas.data.model.TagihanModel
import com.example.tugas.data.remote.request.*
import com.example.tugas.data.remote.response.BaseResponse
import com.example.tugas.data.remote.response.GeneralResponse
import com.example.tugas.data.remote.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {
    /*
     * Example to add other headers
     *
     *  @POST(Endpoints.DUMMY_PROTECTED)
        fun sampleDummyProtectedCall(
            @Body request: DummyRequest,
            @Header(Networking.HEADER_USER_ID) userId: String, // pass using UserRepository
            @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String, // pass using UserRepository
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
        ): Single<DummyResponse>
     */
    @POST(Endpoints.login)
    fun doLoginCall(
        @Body request: LoginRequest,
    ): Single<LoginResponse>

    @POST(Endpoints.register)
    fun doRegisterCall(
        @Body request: RegisterRequest,
    ): Single<LoginResponse>

    @POST(Endpoints.sendemail)
    fun dosendemailCall(
        @Body request: SendmailRequest,
    ): Single<LoginResponse>

    @POST(Endpoints.resetpassword)
    fun doResetPasswordCall(
        @Body request: ResetPasswordRequest,
    ): Single<LoginResponse>

    @POST(Endpoints.get_tahun)
    fun doGetTahunCall(
        @Body request: GetTahunRequest,
    ): Single<LoginResponse>

    @POST(Endpoints.get_data)
    fun doGetPembayaranCall(
        @Body request: GetPembayaranRequest,
    ): Single<LoginResponse>

    @POST(Endpoints.get_tagihan)
    fun doGetTagihanaktifCall(
        @Body request: GetTagihanRequest,
    ): Single<GeneralResponse<TagihanModel>>


    fun doGetTagihanCall(
        @Body request: GetTagihanRequest,
    ): Single<GeneralResponse<List<RiwayatTagihanModel>>>
    @POST(Endpoints.get_tagihan_list)
    fun doGetTagihanlistCall(
        @Body request: GetTagihanRequest,
    ): Single<GeneralResponse<List<RiwayatTagihanModel>>>


    @POST(Endpoints.get_history)
    fun doGetHistoryTagihanCall(
        @Body request: GetTagihanRequest,
    ): Single<GeneralResponse<List<RiwayatTagihanModel>>>

    @POST(Endpoints.get_notif)
    fun doGetListNotifikasi(
        @Body request: GetTagihanRequest,
    ): Single<LoginResponse>

    @POST(Endpoints.get_image)
    fun dobackgroundhomeCall(
    ): Single<GeneralResponse<List<BannerModel>>>

//
//    @GET(Endpoints.GET_KATEGORI_PAGING)
//    fun doMstKategoriListCall(
//        @Query("search") search: String,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<MasterKategoriResponse>
//
//    @POST(Endpoints.POST_KATEGORI)
//    fun doMstKategoriStoreCall(
//        @Body request: KategoriRequest,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<GeneralResponse>
//
//    @GET(Endpoints.GET_KATEGORI_BY_ID)
//    fun doMstKategoriEditCall(
//        @Path("kategori") kategori: String,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<BaseResponse<Kategori>>
//
//    @PUT(Endpoints.PUT_KATEGORI)
//    fun doMstKategoriUpdateCall(
//        @Path("kategori") kategori: String,
//        @Body request: KategoriRequest,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<GeneralResponse>
//
//    @DELETE(Endpoints.DELETE_KATEGORI)
//    fun doMstKategoriDeleteCall(
//        @Path("kategori") kategori: String,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<GeneralResponse>
//
//    @POST(Endpoints.GET_PRODUK_PAGING)
//    fun doMstProdukListCall(
//        @Body request: SearchRequest,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<MasterProdukResponse>
//
//    @GET(Endpoints.GET_POS_KATEGORI)
//    fun doPosKategoriListCall(
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<KategoriListResponse>
//
//    @GET(Endpoints.GET_POS_PRODUK_BY_KATEGORI)
//    fun doPosProdukByKategoriListCall(
//        @Path("kategori") kategori: String = "",
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<ProdukListResponse>
//
//    @POST(Endpoints.POST_POS_STORE)
//    fun doPosStore(
//        @Body request: PosStoreRequest,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<GeneralResponse>

}