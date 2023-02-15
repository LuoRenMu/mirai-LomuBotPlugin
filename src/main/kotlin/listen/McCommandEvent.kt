package com.example.listen

import com.example.Config
import com.example.socket.SocketConsoleClient
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.PlainText


/**
 *    @author LoMu
 *    Date  2023-02-15 18:23
 */
object McCommandEvent {
    fun listenForMcCommand() {
      val channel = GlobalEventChannel.filter { it is MessageEvent && Config.ADMIN_QQ.contains(it.sender.id) && it.message.contentToString().startsWith("执行命令") }
        channel.subscribeAlways<MessageEvent>{event ->
            val message = event.message
            val sendAdminCommand = SocketConsoleClient.sendAdminCommand(message.contentToString().replace("执行命令",""))

            if(event is GroupMessageEvent) {
                event.group.sendMessage(At(event.sender.id)+PlainText("命令已执行"))
                if (sendAdminCommand != null) {
                    sender.sendMessage(sendAdminCommand)
                }
            }

        }
    }
}