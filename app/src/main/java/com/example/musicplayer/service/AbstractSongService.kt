package com.example.musicplayer.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import com.example.musicplayer.Song
import com.example.musicplayer.player.SongPlayer

/*
* Save some boilerplate code
* */
abstract class AbstractSongService
    : Service(), SongPlayer {
    lateinit var playlist: ArrayList<Song>

    lateinit var songPlayer: SongPlayer

    abstract fun initPlayer(intent: Intent?)


    override fun play() {
        songPlayer.play()
    }

    override fun pause() {
        songPlayer.pause()
    }

    override fun stop() {
        songPlayer.stop()
    }

    override fun reset() {
        songPlayer.reset()
    }

    override fun select(index: Int) {
        songPlayer.select(index)
    }

    override fun playNext() {
        songPlayer.playNext()
    }

    override fun playPrevious() {
        songPlayer.playPrevious()
    }

    override fun isPLaying(): Boolean {
        return songPlayer.isPLaying()
    }

    override fun getSelectedSong(): Song? {
        return songPlayer.getSelectedSong()
    }

    override fun getPlaylist(): List<Song> {
        return songPlayer.getPlaylist()
    }
}