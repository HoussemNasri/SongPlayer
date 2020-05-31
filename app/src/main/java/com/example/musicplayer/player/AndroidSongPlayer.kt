package com.example.musicplayer.player

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.example.musicplayer.Song
import com.example.state.PlayerState

class AndroidSongPlayer(
    private val context: Context,
    private val playlist: List<Song>,
    private var selectedPlaylistItem: Int = 0
) : SongPlayer {

    private var playerState = PlayerState.STOPPED
    private val mediaPlayer = MediaPlayer()

    override fun play() {
        if (!isIndexValid(selectedPlaylistItem))
            return
        select(selectedPlaylistItem)
        if (playerState == PlayerState.STOPPED)
            prepare()

        mediaPlayer.start()

        playerState = PlayerState.PLAYING
    }

    private fun prepare() {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(context, Uri.parse(playlist[selectedPlaylistItem].path))
        mediaPlayer.prepare()
    }

    override fun pause() {
        if (!isPLaying())
            return
        mediaPlayer.pause()
        playerState = PlayerState.PAUSED
    }

    override fun stop() {
        if (!isPLaying())
            return
        mediaPlayer.stop()
        playerState = PlayerState.STOPPED
    }

    override fun reset() {
        mediaPlayer.reset()
        select(0)
    }

    override fun select(index: Int) {
        if (!isIndexValid(index))
            return
        selectedPlaylistItem = index
    }

    override fun playNext() {
        if (!isIndexValid(selectedPlaylistItem + 1))
            return
        select(selectedPlaylistItem + 1)
        stop()
        play()

    }

    override fun playPrevious() {
        if (!isIndexValid(selectedPlaylistItem - 1))
            return

        select(selectedPlaylistItem - 1)
        stop()
        play()
    }

    override fun isPLaying(): Boolean {
        return playerState == PlayerState.PLAYING
    }

    override fun getSelectedSong(): Song? {
        if (!isIndexValid(selectedPlaylistItem))
            return null

        return playlist[selectedPlaylistItem]
    }

    override fun getPlaylist(): List<Song> {
        return playlist
    }

    private fun isIndexValid(index: Int): Boolean {
        return index >= 0 && index < playlist.size
    }
}