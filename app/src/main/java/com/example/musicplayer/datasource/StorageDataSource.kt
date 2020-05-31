package com.example.musicplayer.datasource

import android.content.Context
import android.database.Cursor
import android.media.MediaMetadataRetriever
import android.provider.MediaStore
import com.example.musicplayer.Song


class StorageDataSource(
    private val context: Context
) : SongDataSource {

    override fun loadSongs(): List<Song> {
        val songList = ArrayList<Song>()

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.TITLE,
            MediaStore.Audio.ArtistColumns.ARTIST,
            MediaStore.Audio.AudioColumns._ID,
            MediaStore.Audio.AudioColumns.DURATION
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"

        val c: Cursor? = context.contentResolver.query(
            uri,
            projection,
            selection,
            null,
            null
        )
        if (c != null) {
            while (c.moveToNext()) {
                val song = Song()
                val path: String = c.getString(0)
                val name: String = c.getString(1)
                val artist: String = c.getString(2)
                val id = c.getLong(3)
                val duration = c.getLong(4)

                song.id = id
                song.title = name
                song.artist = artist
                song.path = path
                song.duration = duration

                songList.add(song)
            }
            c.close()
        }
        return songList
    }
}