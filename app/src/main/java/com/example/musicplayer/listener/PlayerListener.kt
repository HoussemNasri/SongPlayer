package com.example.musicplayer.listener

interface PlayerListener {
    fun onPlayerStarted()
    fun onPlayerPaused()
    fun onPlayerStopped()
    fun onPlayerReset()

}