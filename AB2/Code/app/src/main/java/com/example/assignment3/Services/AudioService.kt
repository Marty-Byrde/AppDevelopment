package com.example.assignment3.Services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.example.assignment3.R

class AudioService : Service() {
    private lateinit var player: MediaPlayer;

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(this, R.raw.emailsound);
        player.seekTo(0)
        player.isLooping = true;
        player.start();

        Log.d("AppDev", "Service has been started!")

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.d("AppDev", "Service has been stopped!")
        player.release()

        super.onDestroy()
    }

}