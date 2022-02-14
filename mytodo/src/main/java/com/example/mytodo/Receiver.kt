package com.example.mytodo

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d(TAG, "onReceive: ${p1?.action }")
        when (p1?.action) {
            Intent.ACTION_SCREEN_OFF -> {
                Log.d(TAG, "onReceive: screen off")
            }
        }
    }
}