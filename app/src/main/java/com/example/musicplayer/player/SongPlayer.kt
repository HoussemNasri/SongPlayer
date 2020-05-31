package com.example.musicplayer.player

import com.example.musicplayer.Song

interface SongPlayer {
    fun play()
    fun pause()
    fun stop()
    fun reset()
    fun select(index: Int)
    fun playNext()
    fun playPrevious()
    fun isPLaying(): Boolean
    fun getSelectedSong(): Song?
    fun getPlaylist(): List<Song>
}