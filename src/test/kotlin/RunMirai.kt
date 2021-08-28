package org.example.mirai.plugin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader
import java.util.*

suspend fun main() {
    MiraiConsoleTerminalLoader.startAsDaemon()

    //如果是kotlin
    PluginMain.load()
    PluginMain.enable()


    //下面填机器人信息
    val bot = MiraiConsole.addBot(qqnum, "password") {
        fileBasedDeviceInfo()
    }.alsoLogin()




    MiraiConsole.job.join()

}
