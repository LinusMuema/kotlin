package com.moose.androidkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageUrl = "https://lh3.googleusercontent.com/d/11W-vdMfCgHOZf-kFpU0vJbWuFt3whdfC=s220?authuser=0"
        url_image.loadUrl(imageUrl)
        drawable_image.loadDrawable(R.drawable.ic_android)
        random.randomText()
        toast.setOnClickListener { showToast() }
        snackbar.setOnClickListener { showSnackbar(parent_view) }
    }
}