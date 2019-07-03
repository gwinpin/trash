package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()

) {

    abstract fun formatMessage(): String

    companion object AbstractFactory {
        var latId = -1

        fun makeMassage(from: User?,chat: Chat,date: Date = Date(),type: String = "text", payload: Any?): BaseMessage {
            latId++

            return when (type) {
                "image"-> ImageMessage("$latId", from = from, chat = chat,date = date,image = payload as String )//,isIncoming = true)
                else -> TextMessage("$latId", from = from, chat = chat,date = date,text = payload as String )
            }
        }
    }
}