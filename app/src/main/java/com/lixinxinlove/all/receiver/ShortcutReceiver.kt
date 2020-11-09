package com.lixinxinlove.all.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ShortcutReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.e("ShortcutReceiver", "onReceive: " + intent);
        if (Intent.ACTION_LOCALE_CHANGED.equals(intent.getAction())) {
            // Refresh all shortcut to update the labels.
            // (Right now shortcut labels don't contain localized strings though.)
             //ShortcutHelper(context).refreshShortcuts(/*force=*/ true);
        }
    }
}