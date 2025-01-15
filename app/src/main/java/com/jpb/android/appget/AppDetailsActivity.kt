package com.jpb.appget.lite

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
        val bundle = intent.extras
        val appName = bundle?.getString("App")
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
        val appTitleTextView = this.findViewById<TextView>(R.id.AppTitle)
        installbut.setOnClickListener(View.OnClickListener {
            val downloadmanager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val uri =
                Uri.parse("https://github.com/jpbandroid/AppGet-Resources/raw/main/"+appName+"/app-debug.apk")

            val request = DownloadManager.Request(uri)
            request.setTitle(appName)
            request.setDescription("Downloading")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, appName + ".apk")
            downloadmanager.enqueue(request)
        })
        supportActionBar?.title = appName
        appTitleTextView.text = appName
        fetchAppInfo(appName, 5)
        fetchAppInfo(appName, 2)
        fetchAppInfo(appName, 6)
        fetchAppInfo(appName, 7)
        fetchAppInfo(appName, 1)
        fetchAppInfo(appName, 3)
        fetchAppInfo(appName, 4)
    }

    private fun fetchAppInfo(AppName: String?, Mode: Int?) {
        object : Thread() {
            override fun run() {
                val path: String
                when (Mode) {
                    1 -> {path =
                    "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+AppName+"/developerName.txt"}
                    2 -> {path =
                        "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+AppName+"/version.txt"}
                    3 -> {path =
                        "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+AppName+"/minapi.txt"}
                    4 -> {path =
                        "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+AppName+"/targetapi.txt"}
                    5 -> {path =
                        "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+AppName+"/updatedDate.txt"}
                    6 -> {path =
                        "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+AppName+"/description.txt"}
                    7 -> {path =
                        "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+AppName+"/changelog.txt"}
                    else -> {path =
                        "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+AppName+"/developerName.txt"}
                }
                var u: URL?
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
                        when (Mode) {
                            1 -> {val text = findViewById<View>(R.id.AppDev) as TextView
                                text.text = bo.toString()}
                            2 -> {val text = findViewById<View>(R.id.AppVersion) as TextView
                                text.text = "Version: $bo"}
                            3 -> {val text = findViewById<View>(R.id.MinAPIDetails) as TextView
                                text.text = "API $bo"}
                            4 -> {val text = findViewById<View>(R.id.TargetAPIDetails) as TextView
                                text.text = "API $bo"}
                            5 -> {val text = findViewById<View>(R.id.AppReleaseDate) as TextView
                                text.text = "Updated: $bo"}
                            6 -> {val text = findViewById<View>(R.id.ShortDesc) as TextView
                                text.text = bo.toString()}
                            7 -> {val text = findViewById<View>(R.id.Changelog) as TextView
                                text.text = bo.toString()}
                            else -> {val text = findViewById<View>(R.id.AppDev) as TextView
                                text.text = bo.toString()}
                        }
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