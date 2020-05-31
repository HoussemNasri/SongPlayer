package com.example.musicplayer

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import com.example.musicplayer.player.PlayerState
import com.example.musicplayer.service.SongService
import com.example.musicplayer.ui.songplaylist.SongPlaylistFragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.ArrayList

public const val EXTRA_PLAYLIST_OBJECT_KEY = "playlist_key"

class MainActivity : AppCompatActivity(), PermissionListener {

    private var mIsBound = false
    private var mService: SongService? = null
    private var playerState = PlayerState.STOPPED

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mIsBound = true
            val binder = service as SongService.SongBinder
            mService = binder.service
            //setupButtonListener()
            Timber.d("onServiceConnected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mIsBound = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        Dexter.withContext(this)
            .withPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).withListener(this)
            .check()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentPlaceHolder, SongPlaylistFragment.newInstance())
        fragmentTransaction.commit()
    }


    override fun onStart() {
        super.onStart()
        // bindService()

    }

   /* fun setupButtonListener() {
        playerControlButton.setOnClickListener {
            if (playerState == PlayerState.STOPPED || playerState == PlayerState.PAUSED) {
                mService?.play()
                playerState = PlayerState.PLAYING
                playerControlButton.setText("Pause")
            } else {
                mService?.pause()
                playerState = PlayerState.PAUSED
                playerControlButton.setText("Play")

            }
        }

        nextSongButton.setOnClickListener {
            mService?.playNext()
        }

        prevSongButton.setOnClickListener {
            mService?.playPrevious()
        }
    }*/


    override fun onStop() {
        super.onStop()
        // unbindService()
    }

    private fun loadPlaylist(): ArrayList<out Parcelable>? {
        val songStore = SongStore(this)
        return songStore.getAllSongs() as ArrayList<out Parcelable>?
    }

    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
        Timber.d("Granted")
    }

    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
        TODO("Not yet implemented")
    }


    /**
     * Used to bind to our service class
     */
    private fun bindService() {
        Intent(this, SongService::class.java).also { intent ->
            intent.putParcelableArrayListExtra(EXTRA_PLAYLIST_OBJECT_KEY, loadPlaylist())
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
            Timber.d("Intent()")
        }
        Timber.d("bindService()")
    }

    /**
     * Used to unbind and stop our service class
     */
    private fun unbindService() {
        Intent(this, SongService::class.java).also { intent ->
            unbindService(mServiceConnection)
        }
    }
}
