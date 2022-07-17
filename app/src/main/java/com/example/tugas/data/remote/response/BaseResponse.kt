package com.example.tugas.data.remote.response

class BaseResponse<T> {
    var status: Int? = 0
    var message: String? = ""
    var data: T? = null
}