package com.example.musicplayer.datasource

import com.example.musicplayer.Song

interface SongDataSource {

    fun loadSongs(): List<Song>
}