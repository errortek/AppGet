package com.jpb.android.appget

import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class AppDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val appVersionTextView = this.findViewById<TextView>(R.id.AppVersion)
        val appReleaseDateTextView = this.findViewById<TextView>(R.id.AppReleaseDate)
        val ShortDescriptionTextView = this.findViewById<TextView>(R.id.ShortDesc)
        val ChangelogTextView = this.findViewById<TextView>(R.id.Changelog)
        val installbut = this.findViewById<Button>(R.id.InstallButton)
        installbut.setOnClickListener(View.OnClickListener {
            val downloadmanager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val uri =
                Uri.parse("https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/app-debug.apk")

            val request = DownloadManager.Request(uri)
            request.setTitle("AppGet")
            request.setDescription("Downloading")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "AppGet.apk")
            downloadmanager.enqueue(request)
        })
        readUpdateDate()
        readVersion()
        readDescription()
        readChangelog()
    }

    private fun readUpdateDate() {
        object : Thread() {
            override fun run() {
                val path =
                    "https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/updated_date.txt"
                var u: URL? = null
                try {
                    u = URL(path)
                    val c = u.openConnection() as HttpURLConnection
                    c.requestMethod = "GET"
                    c.connect()
                    val `in` = c.inputStream
                    val bo = ByteArrayOutputStream()
                    val buffer = ByteArray(1024)
                    `in`.read(buffer) // Read from Buffer.
                    bo.write(buffer) // Write Into Buffer.

                    runOnUiThread {
                        val text = findViewById<View>(R.id.AppReleaseDate) as TextView
                        text.text = "Updated: $bo"
                        try {
                            bo.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun readVersion() {
        object : Thread() {
            override fun run() {
                val path =
                    "https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/version.txt"
                var u: URL? = null
                try {
                    u = URL(path)
                    val c = u.openConnection() as HttpURLConnection
                    c.requestMethod = "GET"
                    c.connect()
                    val `in` = c.inputStream
                    val bo = ByteArrayOutputStream()
                    val buffer = ByteArray(1024)
                    `in`.read(buffer) // Read from Buffer.
                    bo.write(buffer) // Write Into Buffer.

                    runOnUiThread {
                        val text = findViewById<View>(R.id.AppVersion) as TextView
                        text.text = "Version: $bo"
                        try {
                            bo.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun readDescription() {
        object : Thread() {
            override fun run() {
                val path =
                    "https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/description.txt"
                var u: URL? = null
                try {
                    u = URL(path)
                    val c = u.openConnection() as HttpURLConnection
                    c.requestMethod = "GET"
                    c.connect()
                    val `in` = c.inputStream
                    val bo = ByteArrayOutputStream()
                    val buffer = ByteArray(1024)
                    `in`.read(buffer) // Read from Buffer.
                    bo.write(buffer) // Write Into Buffer.

                    runOnUiThread {
                        val text = findViewById<View>(R.id.ShortDesc) as TextView
                        text.text = bo.toString()
                        try {
                            bo.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun readChangelog() {
        object : Thread() {
            override fun run() {
                val path =
                    "https://github.com/jpbandroid/AppGet-Resources/raw/main/AppGet/changelog.txt"
                var u: URL? = null
                try {
                    u = URL(path)
                    val c = u.openConnection() as HttpURLConnection
                    c.requestMethod = "GET"
                    c.connect()
                    val `in` = c.inputStream
                    val bo = ByteArrayOutputStream()
                    val buffer = ByteArray(1024)
                    `in`.read(buffer) // Read from Buffer.
                    bo.write(buffer) // Write Into Buffer.

                    runOnUiThread {
                        val text = findViewById<View>(R.id.Changelog) as TextView
                        text.text = bo.toString()
                        try {
                            bo.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}