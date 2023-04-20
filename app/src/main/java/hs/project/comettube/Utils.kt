package hs.project.comettube

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.IOException

fun <T> Context.readData(fileName: String, classT: Class<T>): T? {
    return try {
        val inputStream = this.resources.assets.open(fileName)
        val byteArr = ByteArray(inputStream.available())
        inputStream.read(byteArr)
        inputStream.close()
        Gson().fromJson(String(byteArr), classT)
    } catch (e: IOException) {
        println(e.stackTraceToString())
        null
    } catch (e: JsonSyntaxException) {
        println(e.stackTraceToString())
        null
    }
}