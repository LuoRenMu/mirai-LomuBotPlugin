package com.example.listen

import com.example.Config
import com.example.utils.FileUtil
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.MemberJoinEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.AtAll
import net.mamoe.mirai.message.data.Face
import net.mamoe.mirai.message.data.PlainText

/**
 *    @author LoMu
 *    Date  2023-02-12 8:36
 */
object Event {
    private var timeFlag = System.currentTimeMillis()
      fun groupEvent(){
          val channel = GlobalEventChannel.filter { it is GroupEvent}

          channel.subscribeAlways<GroupEvent> { event ->

              if (event is MemberJoinEvent) {

                  if (Config.MC_GROUP_QQ == event.groupId) {
                      group.sendMessage(
                          At(event.user) +
                                  PlainText("欢迎加入 请阅读公告 客户端于群文件下载,有事联系管理") +
                                  At(280462967) + PlainText("qwq")
                      )
                  }
              }


              if (event is GroupMessageEvent) {
                  val message = event.message
                  if (message[1] == PlainText("6")){
                      FileUtil.sendFileMessage("20230212150540.gif",group)
                  }
                  if (message.contentToString().contains("你配吗")){
                      FileUtil.sendFileMessage("20230212222446.jpg",group)
                  }

                  if (message.contentToString().contains("早上好")){
                          FileUtil.sendFileMessage("03.amr",group)

                  }

                  if(message.contentToString().contains("点歌 安瀬聖-陽炎")){
                       FileUtil.sendFileMessage("安瀬聖-陽炎.amr",group)
                  }



                  for (singleMessage in message) {

                      if (singleMessage is At){
                          if (singleMessage.target == Config.DEFAULT_QQ) {
                              FileUtil.sendFileMessage("FWY0HD367C6TTE.jpg",group)
                          }
                      }
                      if (singleMessage is AtAll){
                          FileUtil.sendFileMessage("FWY0HD367C6TTE.jpg",group)
                      }

                      if (singleMessage.toString().contains("width=512, height=384") && group.id == Config.MC_GROUP_QQ) {
                          val newTime = System.currentTimeMillis()
                          val timeInt = newTime - timeFlag
                          //更新时间
                          timeFlag = newTime
                          if (timeInt > 100_000) {
                              group.sendMessage(PlainText("服主,垃圾服务器又炸咯")+ At(1506301834)+Face(Face.擦汗))
                          }
                      }
                  }
              }

          }
    }
}