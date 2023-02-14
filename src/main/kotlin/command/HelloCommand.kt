package com.example.command

import com.example.PluginMain
import net.mamoe.mirai.console.command.CommandContext
import net.mamoe.mirai.console.command.RawCommand
import net.mamoe.mirai.message.data.MessageChain


/**
 *    @author LoMu
 *    Date  2023-02-12 7:39
 */
object HelloCommand : RawCommand(
    PluginMain, "test", // 使用插件主类对象作为指令拥有者；设置主指令名为 "name"
    // 可选：
    "tt", // 增加两个次要名称
    description = "这是一个测试指令", // 设置描述，将会在 /help 展示
    prefixOptional = true,
) {
    override suspend fun CommandContext.onCommand(args: MessageChain) {
        println(123)
  }
}