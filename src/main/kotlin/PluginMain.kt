package org.example.mirai.plugin

import io.ktor.http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.mamoe.mirai.Bot
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.id
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.plugin.name
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.event.events.*
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.info
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import java.net.URL
import java.util.*
import javax.swing.GroupLayout
import javax.swing.Timer
import kotlin.concurrent.timerTask
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements


/*
使用kotlin版请把
src/main/resources/META-INF.services/net.mamoe.mirai.console.plugin.jvm.JvmPlugin
文件内容改成"org.example.mirai.plugin.PluginMain"也就是当前主类
使用kt可以把java文件夹删除不会对项目有影响

在settings.gradle.kts里改生成的插件.jar名称
build.gradle.kts里改依赖库和插件版本
在主类下的JvmPluginDescription改插件名称，id和版本
用runmiraikt这个配置可以在ide里运行，不用复制到mcl或其他启动器
 */
object HTMLParser {
    fun getElementsByClass(html: String, className: String, name: String): Elements {
        val document = Jsoup.parse(html)
        return document.html(html).getElementsByClass(className)
    }
}

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "org.example.mirai-example",
        name = "插件示例",
        version = "0.1.0"
    )
) {
    override fun onEnable() {
        logger.info { "Plugin loaded" }
        //配置文件目录 "${dataFolder.absolutePath}/"
        //val bot = MiraiConsole.addBot(1406937296, "123456789ab")

        //重要计数器及变量


        //huanxiang
        var flagre3 = 0
        var flagre4 = 0
        var upword4 : String? = null
        var upword5 : SingleMessage? =null

        //news
        val url = URL("http://www.people.com.cn/GB/59476/index.html")
        val html = url.readText(charset("GB2312"))

        var flaghello = 0


        Timer().schedule(object: TimerTask(){
            override fun run() {
                GlobalScope.launch {
                    //replyLimit.clearLimitation()
                    if(flaghello==0){
                        Bot.getInstance(1406937296).getGroupOrFail(811029948L)?.sendMessage("苏醒！千衡bot待机中")
                        flaghello = 1
                    }

                }
            }

        },Date(),1000)
        globalEventChannel().subscribeAlways<GroupMessageEvent>{
            //幻想港口群特供
            if(group.id == 811029948L ) {
                //群消息
                //
                //通过 / 来触发事件
                if (message.contentToString().startsWith("/")) {
                    //退出
                    if (message.contentToString().contains("exit")) {
                        group.sendMessage("你真以为我会走嘛？")
                    }

                    //色子
                    if (message.contentToString().contains("rd")) {
                        val i = (1..100).random()
                        group.sendMessage("你摇到了" + i + "!")

                    }

                    //rp
                    if (message.contentToString().contains("rp")) {
                        val i = (1..100).random()
                        group.sendMessage("你今天的运势为" + i + "，今天也要加油！")

                    }

                    //qcqc
                    if (message.contentToString().contains("qcqc")) {
                        val i = (1..100).random()
                        val strurl1 = "https://img-cdn-mina.tengzhihh.com/assets/s/rblq/"+ i +"_1.jpg"
                        val strurl2 = "https://img-cdn-mina.tengzhihh.com/assets/s/rblq/"+ i +"_2.jpg"
                        val a = URL(strurl1).openStream().toExternalResource()
                        val b = URL(strurl2).openStream().toExternalResource()
                        group.sendMessage(subject.uploadImage(a))
                        group.sendMessage(subject.uploadImage(b))

                    }
                    //news
                    if (message.contentToString().contains("news")) {
                        var topnews : String?=null
                        val newsget = HTMLParser.getElementsByClass(html,"p6","p6")
                        newsget.eachText().forEach(){
                            //println(it)
                            topnews= it
                        }
                        topnews?.replace(' ','\n')?.let { it1 -> group.sendMessage(it1) }
                    }

                    //group.sendMessage(message.contentToString().replace("复读", ""))
                }

                //特殊语句！

                //睡觉
                if (message.contentToString().contains("睡觉去了")) {
                    group.sendMessage("你这个年纪！")
                    group.sendMessage("你怎么睡得着觉！")

                }
                //管人
                if (message.contentToString().contains("妹妹") && message.contentToString().contains("管人")) {
                    group.sendMessage("噫")
                    group.sendMessage("管人痴！")

                }
                //抱抱
                if (message.contentToString().contains("千衡抱")) {
                    group.sendMessage("抱抱！")

                }


                //复读文字

                message.forEach {
                    //循环每个元素在消息里
                    if (it is Image) {
                        //如果消息这一部分是图片
                        //val url = it.queryUrl()
                        var upword1 = it

                        if (upword5 == upword1) {
                            flagre3++

                            if (flagre3==2) {
                                group.sendMessage(upword1)

                            }
                        }
                        else {
                            flagre3 = 0
                        }
                        upword5 = upword1
                        //group.sendMessage("图片，下载地址$url")
                        //group.sendMessage(it.toMessageChain())
                        //group.sendMessage(message)
                    }
                    if (it is PlainText) {
                        //如果消息这一部分是纯文本
                        var upword1 = message.contentToString()

                        if (upword4 == upword1) {
                            flagre4++

                            if (flagre4==2) {
                                group.sendMessage(upword1)

                            }
                        }
                        else {
                            flagre4 = 0
                        }
                        upword4 = upword1

                    }
                }

                if (flagre3 == 5) {
                    flagre3 = 0
                    group.sendMessage("rua！复读停止！")
                    //upword2 = "where is the end of the world"
                    //upword1 = upword2
                }
                if (flagre4 == 5) {
                    flagre4 = 0
                    group.sendMessage("rua！复读停止！")
                    //upword2 = "where is the end of the world"
                    //upword1 = upword2
                }
                //


                if (message.contentToString() == "hi") {
                    //群内发送
                    group.sendMessage("hi")
                    //向发送者私聊发送消息
                    sender.sendMessage("hi")
                    //不继续处理
                    return@subscribeAlways
                }
            }
        }












        globalEventChannel().subscribeAlways<FriendMessageEvent>{
            //好友信息
            sender.sendMessage("hi")
        }
        globalEventChannel().subscribeAlways<NewFriendRequestEvent>{
            //自动同意好友申请
            accept()
        }
        globalEventChannel().subscribeAlways<BotJoinGroupEvent>{
            group.sendMessage("千衡bot！参上！")
        }
    }
}


