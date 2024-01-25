package com.realityexpander.firebasepushnotificationshttpv1

data class SendMessageDto(
    val to: String?,
    val notification: NotificationBody
)

data class NotificationBody(
    val title: String,
    val body: String
)
