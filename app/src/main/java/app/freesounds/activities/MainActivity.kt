package app.freesounds.activities

import app.freesounds.R

import app.freesounds.sounds.SoundViewModel
import app.freesounds.sounds.SoundSectionFragment
import app.freesounds.sounds.SoundRepository
import app.freesounds.sounds.SoundService
import app.freesounds.sounds.Sound

import app.freesounds.kfunctions.commit

import android.os.Environment
import android.os.Bundle

import android.app.DownloadManager.Request
import android.app.Notification

import android.net.Uri

import android.graphics.BitmapFactory

import androidx.lifecycle.lifecycleScope

import com.google.android.material.floatingactionbutton.FloatingActionButton

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.Dispatchers

class MainActivity : BaseActivityWithDownloadCapability(R.layout.main_layout) {
    private val soundRepository = SoundRepository.getInstance()

    companion object {
        @JvmStatic
        val soundViewModel = SoundViewModel()
    }

    private suspend fun resolveApi(): Job {
        return coroutineScope {
           launch {
               withContext(Dispatchers.IO) {
                   val freeSoundApi = Retrofit.Builder()
                        .baseUrl("https://freesound.org/apiv2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build() as SoundService

                   val generalSounds = freeSoundApi.sounds

                   for (sound in generalSounds) {
                       if (sound is Sound) {
                           soundRepository.addSound(sound)
                       }
                   }
               }
           }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            resolveApi()
        }

        supportFragmentManager.commit {
            replace(R.id.fragment_container, SoundSectionFragment(), "sound_section_fragment")
            addToBackStack("sound_section_fragment")
        }
    }

    override fun onStart() {
        super.onStart()

        findViewById<FloatingActionButton>(R.id.search_button).setOnClickListener {

        }
    }

    override fun onNotify(
        title: String,
        content: String,
        smallIcon: Int,
        largeIcon: Int
    ): Unit {
        notificationManager.notify(101, Notification.Builder(this, "NOTIFICATION")
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(smallIcon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, largeIcon))
            .build()
        )
    }

    override fun onDownload(url: String): Unit {
        val splitUrl = url.split("//")[1]
        val request = Request(Uri.parse(url))
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, splitUrl)

        downloadManager.enqueue(request)
    }
}