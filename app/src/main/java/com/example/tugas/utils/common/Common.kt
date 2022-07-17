package com.example.tugas.utils.common

import android.util.Base64
import java.nio.charset.StandardCharsets

object Common {
    fun getEncryptedBase64(text: String): String {
        return String(
            Base64.encode(text.toByteArray(), Base64.DEFAULT),
            StandardCharsets.UTF_8
        )
    }
}