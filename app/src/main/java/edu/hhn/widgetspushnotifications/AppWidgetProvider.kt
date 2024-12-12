package edu.hhn.widgetspushnotifications

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import edu.hhn.widgetspushnotifications.receiver.WidgetActionReceiver
import android.content.ComponentName

class AppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout)

            val incrementIntent = Intent(context, WidgetActionReceiver::class.java).apply {
                action = "INCREMENT_COUNTER"
            }

            val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }

            val incrementPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                incrementIntent,
                flags
            )
            views.setOnClickPendingIntent(R.id.button_increment, incrementPendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
