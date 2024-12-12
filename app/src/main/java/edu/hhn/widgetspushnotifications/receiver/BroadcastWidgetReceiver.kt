package edu.hhn.widgetspushnotifications.receiver

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context

class BroadcastWidgetReceiver : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // Widget-Update-Logik hier
        for (widgetId in appWidgetIds) {
            println("Widget Update for ID: $widgetId")
        }
    }
}
