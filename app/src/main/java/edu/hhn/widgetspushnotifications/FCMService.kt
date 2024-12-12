package edu.hhn.widgetspushnotifications

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        val title = remoteMessage.notification?.title ?: "Default Title"
        val body = remoteMessage.notification?.body ?: "Default Body"
        println("FCM Message Received: $title - $body")
    }
}
