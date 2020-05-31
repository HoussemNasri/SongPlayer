package com.example.musicplayer

import android.content.Context
import com.example.musicplayer.datasource.StorageDataSource
import com.example.musicplayer.repository.SongRepositoryImpl
import java.util.*

class SongStore(
    private val context: Context
) {

    private var playlist: List<Song>? = null

    fun getAllSongs(): List<Song> {
        if (playlist == null) {
            val storageDataSource = StorageDataSource(context)
            val repository = SongRepositoryImpl(listOf(storageDataSource))
            playlist = repository.getAllSongs()
        }
        return playlist ?: Collections.emptyList()
    }
}