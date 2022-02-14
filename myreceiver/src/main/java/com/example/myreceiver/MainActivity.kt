package com.example.myreceiver

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))?.apply {
            Log.d(TAG, "onCreate: ")
            when (getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
                BatteryManager.BATTERY_STATUS_CHARGING -> {
                    when (getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)) {
                        BatteryManager.BATTERY_PLUGGED_USB -> {
                            binding.chargingResultView.text = "USB Plugged"
                            binding.chargingImageView.setImageBitmap(
                                BitmapFactory.decodeResource(
                                    resources,
                                    R.drawable.usb
                                )
                            )
                        }
                        BatteryManager.BATTERY_PLUGGED_AC -> {
                            binding.chargingResultView.text = "AC Plugged"
                            binding.chargingImageView.setImageBitmap(
                                BitmapFactory.decodeResource(
                                    resources,
                                    R.drawable.ac
                                )
                            )
                        }
                    }
                }
                else -> binding.chargingResultView.text = "Not plugged"
            }
            binding.percentResultView.text = "percentage: ${getIntExtra(BatteryManager.EXTRA_LEVEL, -1)}"
        }
        binding.button.setOnClickListener {
            sendBroadcast(Intent(this, MyReceiver::class.java))
        }
    }
}