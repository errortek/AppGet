package com.jpb.android.appget

import com.jpb.android.appget.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class STActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stactivity)
        // assigning ID of the toolbar to a variable
        // assigning ID of the toolbar to a variable
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar

        // using toolbar as ActionBar

        // using toolbar as ActionBar
        setSupportActionBar(toolbar)
        toolbar.title = ""
    }
}