package com.example.musicplayer.service

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.musicplayer.*
import com.example.musicplayer.player.AndroidSongPlayer
import timber.log.Timber

class SongService (): AbstractSongService() {


    override fun initPlayer(intent: Intent?) {
        playlist =
            intent?.getParcelableArrayListExtra(EXTRA_PLAYLIST_OBJECT_KEY)
                ?: ArrayList()
        playlist.forEach {
            Timber.d(it.toString())
        }
        songPlayer = AndroidSongPlayer(this, playlist)
    }

    private val binder = SongBinder()

    override fun onBind(intent: Intent?): IBinder? {
        initPlayer(intent)
        return binder
    }

    inner class SongBinder : Binder() {
        val service: SongService
            get() =
                this@SongService
    }

}