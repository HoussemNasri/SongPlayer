package com.example.musicplayer.repository

import com.example.musicplayer.Song
import com.example.musicplayer.datasource.SongDataSource

class SongRepositoryImpl(
    private val dataSources: List<SongDataSource>
) : SongRepository {
    private val songs = ArrayList<Song>()

    init {
        dataSources.forEach { it ->
            it.loadSongs().forEach{
                songs.add(it)
            }
        }

    }

    override fun getAllSongs(): List<Song> {
        return songs
    }

    override fun getSongById(songId: Long): Song {
        return songs.first { song -> song.id == songId }
    }

    override fun getSongsByArtist(artist: String): List<Song> {
        return songs.filter { song -> song.artist == artist }
    }
}