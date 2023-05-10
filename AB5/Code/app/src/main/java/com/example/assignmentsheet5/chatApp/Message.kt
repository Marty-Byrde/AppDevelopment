package com.example.assignmentsheet5.chatApp

import java.util.*

enum class MessageType{ OUT, IN}
data class Message(val author: String, val date: Date, val text: String, val type: MessageType)
