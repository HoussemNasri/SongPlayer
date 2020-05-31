package com.example.musicplayer.ui.songplaylist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicplayer.Song
import com.example.musicplayer.datasource.StorageDataSource
import com.example.musicplayer.repository.SongRepositoryImpl

class SongPlaylistViewModel(
    val context: Context
) : ViewModel() {
    val _allSongLiveData = MutableLiveData<List<Song>>()
    val allSongsLiveData: LiveData<List<Song>>
        get() = _allSongLiveData

    init {
        val storageSong = StorageDataSource(context)
        val repository = SongRepositoryImpl(listOf(storageSong))
        _allSongLiveData.postValue(repository.getAllSongs())
    }

    fun getAllSongs(): LiveData<List<Song>> {
        return allSongsLiveData
    }
}