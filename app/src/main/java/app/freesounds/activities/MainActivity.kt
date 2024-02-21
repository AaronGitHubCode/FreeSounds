package app.freesounds.activities

import app.freesounds.R

import app.freesounds.sounds.SoundViewModel

import app.freesounds.kfunctions.commit

import android.os.Environment
import android.os.Bundle

import android.app.DownloadManager.Request
import android.app.Notification

import android.net.Uri

import android.graphics.BitmapFactory

import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : BaseActivityWithDownloadCapability(R.layout.main_layout) {

    companion object {
        @JvmStatic
        val soundViewModel = SoundViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.commit {

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