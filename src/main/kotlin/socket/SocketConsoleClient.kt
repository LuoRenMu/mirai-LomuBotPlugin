package com.example.socket

import com.example.Config
import java.io.*
import java.net.Socket
import java.nio.charset.StandardCharsets


/**
 *    @author LoMu
 *    Date  2023-02-15 20:45
 */
object SocketConsoleClient {
    private var socket: Socket = Socket(Config.MC_CONSOLE_IP,Config.MC_CONSOLE_PORT)
    private var reader =  BufferedReader(InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))
    private var writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))

    fun sendAdminCommand(message: String): String? {
        writeLine(message)
        val timeOut  = System.currentTimeMillis() + 10_000
        while (true) {
            if (reader.ready() && reader.readLine() != null) {
                return reader.readLine()
            }
            if (System.currentTimeMillis() > timeOut) {
                return "命令执行失败"
            }
        }
    }

    private fun writeLine(str: String) {
        writer.write(str+"\n")
        writer.flush()
    }

}

