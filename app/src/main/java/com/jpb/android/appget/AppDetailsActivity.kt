package com.jpb.appget.lite

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class AppDetailsActivity : AppCompatActivity() {
    @SuppressLint("Range")
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
            val appIconImageView = findViewById<ImageView>(R.id.AppIcon)
            val downloadmanager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val uri =
                Uri.parse("https://github.com/jpbandroid/AppGet-Resources/raw/main/"+appName+"/app.apk")
            val downloadProgress: CircularProgressIndicator = findViewById(R.id.download_progress)
            downloadProgress.progress = 0
            downloadProgress.visibility = View.VISIBLE
            val thread = Thread {
                try {
                    var bitmap = fetchIcon(appName)
                    appIconImageView.load(bitmap) {
                            transformations(CircleCropTransformation())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            thread.start()

            installbut.text = "Cancel"
            val request = DownloadManager.Request(uri)

            request.setDescription("Downloading")
            request.setTitle(appName)
            request.setMimeType("application/vnd.android.package-archive")
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "app.apk")

            val manager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager

            val downloadId = manager.enqueue(request)

            Thread {
                var downloading = true
                while (downloading) {
                    val q = DownloadManager.Query()
                    q.setFilterById(downloadId)

                    val cursor = manager.query(q)
                    cursor.moveToFirst()
                    val bytes_downloaded = cursor.getInt(
                        cursor
                            .getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                    )
                    val bytes_total =
                        cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))

                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        downloading = false
                        runOnUiThread { downloadProgress.visibility = View.VISIBLE
                            val thread = Thread {
                                try {
                                    var bitmap = fetchIcon(appName)
                                    downloadProgress.visibility = View.GONE
                                    appIconImageView.load(bitmap)
                                    installbut.text = "Install"
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }.start()
                        }
                    }
                    val dl_progress = ((bytes_downloaded * 100L) / bytes_total)

                    runOnUiThread { downloadProgress.progress = dl_progress.toInt() }

                    Log.d("DOWNLOAD", statusMessage(cursor))
                    cursor.close()
                }
            }.start()
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
        fetchAppInfo(appName, 8)
        fetchAppInfo(appName, 9)
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
                    8 -> {path =
                        "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+AppName+"/icon.png"}
                    9 -> {path =
                        "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+AppName+"/size.txt"}
                    else -> {path =
                        "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+AppName+"/developerName.txt"}
                }
                var u: URL?
                try {
                    u = URL(path)
                    val c = u.openConnection() as HttpURLConnection
                    c.requestMethod = "GET"
                    c.connect()
                    when (Mode) {
                        8 -> {
                            val handler = Handler(Looper.getMainLooper())
                            val `in` = URL(path).openStream()
                            val appIconImageView = findViewById<ImageView>(R.id.AppIcon)
                            var bitmap = BitmapFactory.decodeStream(`in`)
                            handler.post{appIconImageView.setImageBitmap(bitmap)}
                        }
                        else -> {
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
                                    9 -> {val text = findViewById<View>(R.id.AppSizeDetails) as TextView
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
                        }}
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun fetchIcon(appName: String?): Bitmap? {
        val path =
            "https://github.com/jpbandroid/AppGet-Resources/raw/main/"+appName+"/icon.png"
        val `in` = URL(path).openStream()
        var bitmap = BitmapFactory.decodeStream(`in`)
        return bitmap
    }

    @SuppressLint("Range")
    private fun statusMessage(c: Cursor): String {

        var msg: String = when (c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                DownloadManager.STATUS_FAILED -> "Download failed"
                DownloadManager.STATUS_PAUSED -> "Download paused"
                DownloadManager.STATUS_PENDING -> "Download pending"
                DownloadManager.STATUS_RUNNING -> "Download in progress"
                DownloadManager.STATUS_SUCCESSFUL -> "Download complete"
                else -> "Download not found"
            }

        return (msg)
    }
}