package com.example

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value


/**
 *    @author LoMu
 *    Date  2023-02-12 22:32
 */
object Config : AutoSavePluginConfig("config"){
    val DEFAULT_QQ: Long by value(2935918608L)
    val MC_GROUP_QQ: Long by value(727572573L)
    val IMAGE_PATH: String by value("C:\\images\\")
    val AUDIO_PATH: String by value("C:\\audio\\")
    val NOT_LISTEN_GROUP: List<Long> by value (listOf(874944256L))
}
