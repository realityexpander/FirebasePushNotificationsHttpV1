package com.realityexpander.firebasepushnotificationshttpv1

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

var remoteToken: String? = null
val latestMessageFlow: MutableSharedFlow<String> = MutableSharedFlow()

class PushNotificationService: FirebaseMessagingService() {

    private val coroutineScope = MainScope()

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        // Update our server with new token
        remoteToken = token
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        // Respond to received messages
        println("Message received: ${message.notification?.body}")
        coroutineScope.launch {
            latestMessageFlow.emit(message.notification?.body ?: "")
        }
    }
}
