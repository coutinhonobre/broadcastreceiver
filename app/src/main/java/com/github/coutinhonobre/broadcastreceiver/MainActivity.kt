package com.github.coutinhonobre.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val mReceiver = CustomReceiver()

    private val ACTION_CUSTOM_BROADCAST: String =
            BuildConfig.APPLICATION_ID.toString() + ".ACTION_CUSTOM_BROADCAST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        // Register the receiver using the activity context.
        this.registerReceiver(mReceiver, filter);

        sendBroadcastButton.setOnClickListener {

            val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
            LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
            LocalBroadcastManager.getInstance(this)
                    .registerReceiver(mReceiver,
                            IntentFilter(ACTION_CUSTOM_BROADCAST));
            //https://codelabs.developers.google.com/codelabs/android-training-broadcast-receivers/index.html?index=..%2F..android-training#3

        }

    }

    override fun onDestroy() {
        //Unregister the receiver
        this.unregisterReceiver(mReceiver);
        super.onDestroy()
    }
}