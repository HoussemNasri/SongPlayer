package com.example.musicplayer.repository

import com.example.musicplayer.Song

interface SongRepository {
    fun getAllSongs(): List<Song>

    fun getSongById(songId: Long): Song

    fun getSongsByArtist(artist: String): List<Song>
}