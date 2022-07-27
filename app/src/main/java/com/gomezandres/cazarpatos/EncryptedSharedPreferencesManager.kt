package com.gomezandres.cazarpatos

import android.app.Activity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.net.URI.create

class EncryptedSharedPreferencesManager(val activity: Activity): FileHandler {

    override fun SaveInformation(datosAGuardar: Pair<String, String>) {

        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPref = EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            activity,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

        val editor = sharedPref.edit()
        editor.putString(LOGIN_KEY, datosAGuardar.first)
        editor.putString(PASSWORD_KEY,datosAGuardar.second)
        editor.apply()
    }

    override fun ReadInformation(): Pair<String, String> {

        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPref = EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            activity,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

        val email = sharedPref.getString(LOGIN_KEY,"").toString()
        val pass = sharedPref.getString(PASSWORD_KEY,"").toString()
        return (email to pass)
    }

}