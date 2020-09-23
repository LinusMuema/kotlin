package com.moose.androidkt

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

private val chars = ('a'..'Z') + ('A'..'Z') + ('0'..'9')
private fun random() = List(5) { chars.random() }.joinToString("")

fun ImageView.loadUrl(url: String){
    Glide.with(this.context).load(url).into(this)
}

fun ImageView.loadDrawable(id: Int) {
    Glide.with(this.context).asDrawable().load(id).into(this)
}

fun TextView.randomText(){
    this.text = random()
}

fun Context.showToast(){
    Toast.makeText(this, "Random characters: ${random()}", Toast.LENGTH_SHORT).show()
}

fun showSnackbar(view: View){
    Snackbar.make(view, random(), Snackbar.LENGTH_LONG).show()
}
