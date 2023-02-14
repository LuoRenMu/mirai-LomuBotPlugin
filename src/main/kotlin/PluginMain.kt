package com.example


import com.example.utils.FileUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.mamoe.mirai.Bot


import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin

import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.info
import kotlin.random.Random

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "cn.luorenmu.demo",
        name = "LomuBot",
        version = "0.1.0",
    ) {
        author("LuoRenMu")
    }
) {

    override fun onEnable() {
        logger.info { "LomuBot Plugin loaded" }
        Event.groupEvent()
        launch {
            while (true) {
                delay(Random.nextLong(1_800_000, 5_400_000))
                val groups = Bot.getInstance(Config.DEFAULT_QQ).groups
                val group = groups.random()
                val randomMember = group.members.random()
                when (Random.nextInt(1, 9)) {
                    1 -> group.sendMessage(PlainText("嗯? 发生什么了.."))
                    2 -> if (group.id == Config.MC_GROUP_QQ) {
                        when (Random.nextInt(1, 5)) {
                            1 -> group.sendMessage(
                                PlainText("大哥哥~~!") + At(2504039270) + PlainText("  v点") + Face(
                                    Face.委屈
                                )
                            )

                            2 -> group.sendMessage(
                                At(280462967) + PlainText("嗯?.....嗯......嗯!!!!\n") + Face(Face.惊恐) + PlainText(" ..呜呜呜") + Face(
                                    Face.大哭
                                )
                            )

                            3 -> {
                                group.sendMessage(At(1506301834))
                                FileUtils.sendFileMessage("2023-2121504.jpg",group)
                            }

                            4 -> {
                                group.sendMessage(At(3523227924))
                                FileUtils.sendFileMessage("20230212192019.jpg",group)
                            }

                            5 -> {
                                group.sendMessage(
                                    PlainText("大哥哥!!! I NEEDDDDDDD $$$$$$$$$$$$$$!") + Face(Face.流泪) + At(
                                        2830067413
                                    )
                                )
                            }
                        }
                    }


                    3 -> FileUtils.sendFileMessage("20230212191534.gif",group)
                    4 -> {
                        group.sendMessage(At(randomMember.id) + PlainText("少年,你渴望力量吗"))
                        FileUtils.sendFileMessage("20230212191842.png",group)
                    }

                    5 -> FileUtils.sendFileMessage("20230212224001.gif",group)
                    6 -> FileUtils.sendFileMessage("20230212230700.gif",group)
                    7 -> FileUtils.sendFileMessage("20230212230829.gif",group)
                    8 -> FileUtils.sendFileMessage("20230212230910.gif",group)
                    9 -> FileUtils.sendFileMessage("20230212231005.gif",group)
                }
            }

        }
    }
}


