package edu.hhn.widgetspushnotifications.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import edu.hhn.widgetspushnotifications.R
import edu.hhn.widgetspushnotifications.SharedPreferencesManager
import edu.hhn.widgetspushnotifications.AppWidgetProvider

class WidgetActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Handle the broadcast action
        if (intent.action == "INCREMENT_COUNTER") {
            // Increment the counter in SharedPreferences
            SharedPreferencesManager.incrementCounter(context)

            // Get an instance of AppWidgetManager
            val appWidgetManager = AppWidgetManager.getInstance(context)

            // Get the component name of the AppWidgetProvider
            val componentName = ComponentName(context, AppWidgetProvider::class.java)

            // Get all AppWidget IDs associated with this provider
            val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName)

            // Loop through the AppWidget IDs and update each widget
            for (appWidgetId in appWidgetIds) {
                // Get the current counter value from SharedPreferences
                val counter = SharedPreferencesManager.getCounter(context)

                // Create a RemoteViews object for the widget layout
                val views = RemoteViews(context.packageName, R.layout.widget_layout)
                views.setTextViewText(R.id.text_counter, "Counter: $counter")

                // Update the widget
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }
    }
}
