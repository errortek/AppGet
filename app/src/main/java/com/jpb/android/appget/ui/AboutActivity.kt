package com.jpb.android.appget.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jpb.android.appget.R
import com.jpb.android.appget.oss.license.OSSLicense

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val button3: Button = findViewById(R.id.button6)
        button3.setOnClickListener {
            val intent = Intent(applicationContext, OSSLicense::class.java)
            startActivity(intent)
        }
    }
}