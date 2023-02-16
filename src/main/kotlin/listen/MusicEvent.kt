package com.example.listen

import com.example.Config
import com.example.utils.DownloadUtil
import com.example.utils.FileUtil
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.PlainText


/**
 *    @author LoMu
 *    Date  2023-02-15 2:22
 */
object MusicEvent {

    fun listenForMusicEvent(){
        val channel = GlobalEventChannel.filter { it is GroupEvent && !Config.NOT_LISTEN_GROUP.contains(it.group.id) }

        channel.subscribeAlways<GroupMessageEvent>{ event ->
            val message = event.message
            for (singleMessage in message) {
                if(singleMessage.contentToString().startsWith("点歌") || singleMessage.contentToString().startsWith("唱歌")){
                    val contentToString = singleMessage.contentToString()
                    val indexOf  = if (contentToString.contains("点歌")) {
                        contentToString.indexOf("点歌")
                    }else{
                        contentToString.indexOf("唱歌")
                    }
                    val id = contentToString.substring(indexOf+2).replace(" ","")
                    if (!id.matches(Regex("[0-9]+"))) return@subscribeAlways

                    if (id.length >= 10 || id.length < 4){
                        group.sendMessage(PlainText("非法数据请求 ") + At(event.sender.id) + PlainText(" 傻逼!"))
                        return@subscribeAlways
                    }
                    FileUtil.sendFileMessage("sanbing.amr",group)
                    val downloadMusicId = DownloadUtil.downloadMusicId(id)
                    if (downloadMusicId != null) {
                        group.sendMessage("===侦测到在途的网易云音乐(仅支持手机QQ播放)===")
                        FileUtil.sendFileMessage(downloadMusicId,group)
                    }
                    else{
                        group.sendMessage(At(event.sender.id) + PlainText("   音乐无法获取:检查ID是否存在 该音乐不可为VIP音乐(网易云音乐ID)"))
                    }

                }
            }
        }
    }
}