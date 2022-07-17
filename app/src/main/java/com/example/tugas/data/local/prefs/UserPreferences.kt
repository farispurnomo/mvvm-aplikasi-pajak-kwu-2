package com.example.tugas.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_ID = "PREF_KEY_ID"
        const val KEY_USER_ID = "PREF_KEY_USER_ID"
        const val KEY_USER_NAME = "PREF_KEY_USER_NAME"
        const val KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL"
        const val KEY_USER_FOTO = "PREF_KEY_USER_FOTO"
        const val KEY_IS_DAFTAR = "PREF_KEY_IS_DAFTAR"
        const val KEY_IS_NOP = "PREF_KEY_IS_NOP"
    }

    fun getId(): String? = prefs.getString(KEY_ID, null)
    fun setId(id: String) = prefs.edit().putString(KEY_ID, id).apply()
    fun removeId() = prefs.edit().remove(KEY_ID).apply()

    fun getUserId(): String? = prefs.getString(KEY_USER_ID, null)
    fun setUserId(userId: String) = prefs.edit().putString(KEY_USER_ID, userId).apply()
    fun removeUserId() = prefs.edit().remove(KEY_USER_ID).apply()

    fun getUserName(): String? = prefs.getString(KEY_USER_NAME, null)
    fun setUserName(userName: String) = prefs.edit().putString(KEY_USER_NAME, userName).apply()
    fun removeUserName() = prefs.edit().remove(KEY_USER_NAME).apply()

    fun getUserFoto(): String? = prefs.getString(KEY_USER_FOTO, null)
    fun setUserFoto(foto: String) = prefs.edit().putString(KEY_USER_FOTO, foto).apply()
    fun removeUserFoto() = prefs.edit().remove(KEY_USER_FOTO).apply()

    fun getIsDaftar(): Boolean = prefs.getBoolean(KEY_IS_DAFTAR, false)
    fun setIsDaftar(isDaftar: Boolean) = prefs.edit().putBoolean(KEY_IS_DAFTAR, isDaftar).apply()
    fun removeIsDaftar() = prefs.edit().remove(KEY_IS_DAFTAR).apply()

    fun getUserEmail(): String? = prefs.getString(KEY_USER_EMAIL, null)
    fun setUserEmail(email: String) = prefs.edit().putString(KEY_USER_EMAIL, email).apply()
    fun removeUserEmail() = prefs.edit().remove(KEY_USER_EMAIL).apply()

    fun getusernop(): String? = prefs.getString(KEY_IS_NOP, null)
    fun setusernop(nop: String) = prefs.edit().putString(KEY_IS_NOP, nop).apply()
    fun removeusernop() = prefs.edit().remove(KEY_IS_NOP).apply()


}