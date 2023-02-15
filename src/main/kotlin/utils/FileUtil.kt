package com.example.utils

import com.example.Config
import com.example.model.FileSuffix
import com.example.model.FileSuffix.IMAGE
import com.example.model.FileSuffix.MUSIC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import java.io.BufferedInputStream
import java.io.File


/**
 *    @author LoMu
 *    Date  2023-02-12 19:45
 */
object FileUtil {

    private fun getImagePath(imageName: String) = File("${Config.IMAGE_PATH}$imageName")

    private fun getAudioPath(audioName: String) = File("${Config.AUDIO_PATH}$audioName")

    private fun File.toBufferedInputStream(): BufferedInputStream = this.inputStream().buffered()



    private fun suffix(fileName: String): FileSuffix? {
        val lastIndexOf = fileName.lastIndexOf(".")
        return when(fileName.substring(lastIndexOf + 1)){
            "gif","jpg","png" -> IMAGE
            "mp3","amr","wav" -> MUSIC
            else -> return null
        }
    }

    suspend fun sendFileMessage(fileName: String, group: Group): Boolean {
        val file: File?
        val fileType: FileSuffix
        if (suffix(fileName) == IMAGE) {
            file = getImagePath(fileName)
            fileType = IMAGE
        }else  if (suffix(fileName) == MUSIC) {
            file =  getAudioPath(fileName)
            fileType = MUSIC
        } else {
            group.sendMessage(PlainText("内部发生错误 Data\n fileName:$fileName\n"))
            return false
        }
        val bufferedInputStream: BufferedInputStream
        try {
             bufferedInputStream = file.toBufferedInputStream()
        }catch (e: Exception){
            group.sendMessage("内部发送错误")
            e.printStackTrace()
            return false
        }

        val resource = bufferedInputStream.toExternalResource()
        when (fileType) {
                IMAGE -> group.sendImage(resource)
                MUSIC -> group.sendMessage(group.uploadAudio(resource))
         }
        withContext(Dispatchers.IO) {
            resource.close()
        }
        return true
    }
}