package com.example.musicplayer

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(
    var id: Long = -1,
    var title: String? = "None",
    var artist: String? = "None",
    var path: String? = "",
    var duration: Long = -1
) : Parcelable {

    override fun toString(): String {
        return "Song(id=$id, title=$title, artist=$artist, path=$path, duration=${TimeUtils.formateMilliSeccond(
            duration
        )})"
    }
}