package com.example.musicplayer.ui.songplaylist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayer.R
import com.example.musicplayer.Song
import kotlinx.android.synthetic.main.song_playlist_fragment.*
import timber.log.Timber

class SongPlaylistFragment : Fragment() {

    companion object {
        fun newInstance() = SongPlaylistFragment()
    }

    private lateinit var viewModelFactory: SongPlaylistViewModelFactory

    private lateinit var viewModel: SongPlaylistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.song_playlist_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelFactory = this.context?.let { SongPlaylistViewModelFactory(it) }!!

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SongPlaylistViewModel::class.java)
        viewModel.getAllSongs().observe(viewLifecycleOwner, Observer { songList ->
            val recyclerViewAdapter = SongPlaylistAdapter(songList as ArrayList<Song>) {}
            rvSongPlaylist.layoutManager = LinearLayoutManager(context)
            rvSongPlaylist.adapter = recyclerViewAdapter
        })

        Timber.d("onActivityCreated()")
    }

}