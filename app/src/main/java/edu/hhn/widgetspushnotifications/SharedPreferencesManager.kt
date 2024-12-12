package edu.hhn.widgetspushnotifications

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {
    private const val PREFS_NAME = "WidgetPrefs"
    private const val KEY_COUNTER = "counter"

    fun getCounter(context: Context): Int {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_COUNTER, 0) // Default to 0 if not found
    }

    fun incrementCounter(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val currentCounter = getCounter(context)
        prefs.edit().putInt(KEY_COUNTER, currentCounter + 1).apply()
    }
}
