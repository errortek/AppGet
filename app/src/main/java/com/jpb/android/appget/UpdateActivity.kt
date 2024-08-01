package com.jpb.android.appget

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.PackageInfoCompat
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.card.MaterialCardView
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class UpdateActivity : AppCompatActivity() {
    var onlineVersionCode = 0
    var versionCode = 0
    var drawable_appget : Drawable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        readVersion()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val appgeticon = findViewById<ImageView>(R.id.appgeticon)
        val appget_update = findViewById<Button>(R.id.button6)
        val appget_progress = findViewById<CircularProgressIndicator>(R.id.appget_progress)
        appget_update.setOnClickListener{
        if (appget_update.text != "Cancel") {
            appget_progress.visibility = View.VISIBLE
            appgeticon.load(R.mipmap.ic_launcher) {
                    transformations(CircleCropTransformation())
            }
            appget_update.text = "Cancel"
            } else {
              appget_progress.visibility = View.GONE
              appgeticon.load(R.mipmap.ic_launcher)
              appget_update.text = "Install"
            }
        }
        try {
            val packageInfo = packageManager.getPackageInfo("com.jpb.scratchtappy", PackageManager.GET_META_DATA)
            // Retrieve the version information
            versionCode = PackageInfoCompat.getLongVersionCode(packageInfo).toInt()
        } catch (e: PackageManager.NameNotFoundException) {
            val cardView = findViewById<MaterialCardView>(R.id.cardU)
            cardView.visibility = View.GONE
            e.printStackTrace()
        }
        val cardView = findViewById<MaterialCardView>(R.id.cardU)
        if (versionCode > onlineVersionCode) {
           cardView.visibility = View.GONE
        }
    }


    private fun readVersion() {
        object : Thread() {
            override fun run() {
                val path =
                    "https://github.com/jpbandroid/AppGet-Resources/raw/main/USP/version_int.txt"
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
                        runOnUiThread { // Get the package manager instance
                            val packageManager = packageManager

                            try {
                                // Get the package information
                                val packageInfo =
                                    packageManager.getPackageInfo("com.jpb.scratchtappy", 0)

                                // Retrieve the version information
                                val versionCode = packageInfo.versionCode
                                val text = findViewById<View>(R.id.textView24) as TextView
                                text.text = "$bo"
                                onlineVersionCode = bo.size()
                            } catch (e: PackageManager.NameNotFoundException) {
                                e.printStackTrace()
                                val text = findViewById<View>(R.id.textView24) as TextView
                                //text.text = "$bo"
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