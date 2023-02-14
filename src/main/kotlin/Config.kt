package com.example

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value


/**
 *    @author LoMu
 *    Date  2023-02-12 22:32
 */
object Config : AutoSavePluginConfig("Setting"){
    val DEFAULT_QQ: Long by value(2935918608L)
    val MC_GROUP_QQ: Long by value(727572573L)
    val IMAGE_PATH: String by value("C:\\Images\\")
    val AUDIO_PATH: String by value("C:\\Images\\")

}
