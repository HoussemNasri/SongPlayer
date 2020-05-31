package com.example.musicplayer.ui.songplaylist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SongPlaylistViewModelFactory(
    val context: Context
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SongPlaylistViewModel(context) as T
    }
}