package edu.hhn.widgetspushnotifications

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.hhn.firebaseconnector.NotificationHelper

class CounterViewModel : ViewModel() {
    var counter = mutableStateOf(0)
        private set

    fun increment() {
        counter.value++
    }

    fun decrement() {
        if (counter.value > 0) counter.value--
    }

    fun sendNotification(title: String, message: String) {
        NotificationHelper.sendNotification(
            title = title,
            message = message,
            callback = { success, info ->
                if (success) {
                    println("Notification sent successfully!")
                } else {
                    println("Failed to send notification: $info")
                }
            }
        )
    }
}
