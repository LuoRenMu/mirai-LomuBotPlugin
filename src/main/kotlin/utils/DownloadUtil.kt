package com.example.utils

import com.example.Config
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.file.Files


/**
 *    @author LoMu
 *    Date  2023-02-13 22:15
 */
object DownloadUtil {

    private const val MUSIC_163_MUSIC_ID_URL = "http://music.163.com/song/media/outer/url?id="


    private fun getMusicUrl(id: String) = "$MUSIC_163_MUSIC_ID_URL$id.mp3"

    private fun getMusicPath(id: String) = "${Config.AUDIO_PATH}$id.mp3"

    fun downloadMusicId(id: String): String? {
        val filePath = File(Config.AUDIO_PATH)
        if (!filePath.exists()){
            filePath.mkdirs()
        }
        if (File(getMusicPath(id)).exists()){
            return "$id.mp3"
        }
         val url = URL(getMusicUrl(id))
         val openConnection = url.openConnection()
         val inputStream = openConnection.getInputStream()

         val fileOutputStream = FileOutputStream(getMusicPath(id))

         fileOutputStream.write(inputStream.readBytes())
         fileOutputStream.flush()
         inputStream.close()
         fileOutputStream.close()


        val file = File(getMusicPath(id))
        if (Files.size(file.toPath()) < 10){
            println(Files.size(file.toPath()))
            file.delete()
            println("脏数据已删除")
            return null
        }
        return "$id.mp3"
    }

    //TODO 我不会
    fun downloadMusicName(name: String){

    }
}