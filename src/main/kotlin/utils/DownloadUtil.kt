package com.example.utils

import java.io.File
import java.io.FileOutputStream
import java.net.URL




/**
 *    @author LoMu
 *    Date  2023-02-13 22:15
 */
object DownloadUtil {

    private const val MUSIC_163_MUSIC_URL = "http://music.163.com/song/media/outer/url?id="


    private fun getMusicUrl(id: String): String {
        return "$MUSIC_163_MUSIC_URL$id.mp3"
    }

    fun downloadMusic(id: String): String? {
        if (File("$id.mp3").exists()){
            return "$id.mp3"
        }
         val url = URL(getMusicUrl(id))
         val openConnection = url.openConnection()
         val inputStream = openConnection.getInputStream()
         val fileOutputStream = FileOutputStream("$id.mp3")
         fileOutputStream.write(inputStream.readBytes())
         fileOutputStream.flush()
         inputStream.close()
         fileOutputStream.close()


        val file = File("$id.mp3")
        if (file.length() < 200){
            file.delete()
            println("脏数据已删除")
            return null
        }
        return "$id.mp3"
    }
}