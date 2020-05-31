package com.example.musicplayer.ui.songplaylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.Song

class SongPlaylistAdapter(val items: ArrayList<Song>, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<SongPlaylistAdapter.ViewHolder>() {
    private var tempTitleTextView: TextView? = null
    private var tempArtistTextView: TextView? = null
    private var prevSelected = -1;
    private var selected = -1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.song_item, parent, false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], position, listener)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Song, pos: Int, listener: (Int) -> Unit) = with(itemView) {
            val tvSongTitle = itemView.findViewById(R.id.tvSongTitle) as TextView
            val tvSongArtist = itemView.findViewById(R.id.tvSongArtist) as TextView
            tvSongTitle.text = item.title ?: "Unknown Title"
            tvSongArtist.text = item.artist ?: "Unknown Artist"
            tvSongTitle.setTextAppearance(R.style.IdleSongTitleAppearance)
            tvSongArtist.setTextAppearance(R.style.IdleSongArtistAppearance)
            if(pos == selected) {
                tvSongTitle.setTextAppearance(R.style.SelectedSongTitleAppearance)
                tvSongArtist.setTextAppearance(R.style.SelectedSongArtistAppearance)
            }

            if(pos == prevSelected) {
                tempTitleTextView?.setTextAppearance(R.style.IdleSongTitleAppearance)
                tempArtistTextView?.setTextAppearance(R.style.IdleSongArtistAppearance)
            }


            itemView.setOnClickListener {
                prevSelected = selected
                selected = pos

                notifyDataSetChanged()

            }
        }
    }
}