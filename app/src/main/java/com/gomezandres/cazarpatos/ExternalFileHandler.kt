package com.gomezandres.cazarpatos

import android.app.Activity
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream

class ExternalFileHandler(private val actividad: Activity): FileHandler {

    override fun SaveInformation(datosAgrabar: Pair<String,String>) {
        val username = datosAgrabar.first
        val password = datosAgrabar.second
        Log.d(TAG,"Persisting authentication details\nusername->$username" +
                "\npassword -> $password")
        File(SHARED_INFO_FILENAME).bufferedWriter().use { outputStream ->
                Log.d(TAG,"Successful writing")
                outputStream.write(username)
                outputStream.write(System.lineSeparator())
                outputStream.write(password)
        }
    }

    override fun ReadInformation(): Pair<String,String>{
        Log.d(TAG,"Reading")
        val inputStream: InputStream = File(SHARED_INFO_FILENAME).inputStream()
        inputStream.bufferedReader().use {
                val datoLeido = it.readText()
                val textArray = datoLeido.split(System.lineSeparator())
                val username = textArray[0]
                val password = textArray[1]
                return Pair(username,password)
        }
        return Pair("","")
    }

    companion object{
        const val TAG = "ExternalFileHandler"
    }

}