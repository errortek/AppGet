package com.jpb.android.appget

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class UpdateActivity : AppCompatActivity() {
    private var onlineVersionCode = 0
    var versionCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        readVersion()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        try {
            val packageInfo =
                packageManager.getPackageInfo("com.jpb.scratchtappy", 0)

            // Retrieve the version information
            versionCode = packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        val cardView = findViewById<MaterialCardView>(R.id.cardU)
            if (versionCode < onlineVersionCode) {
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
                                val versionName = packageInfo.versionName
                                val versionCode = packageInfo.versionCode
                                val text = findViewById<View>(R.id.textView24) as TextView
                                text.text = "$versionName->$bo"
                                onlineVersionCode = bo.size()
                            } catch (e: PackageManager.NameNotFoundException) {
                                e.printStackTrace()
                                val text = findViewById<View>(R.id.textView24) as TextView
                                text.text = "e ->$bo"
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