package com.example.fly.my_utils

import android.util.Log
import android.view.View
import android.view.View.*
import android.widget.ImageView
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.fly.R
import com.example.fly.service.cons.TAG
import com.google.gson.Gson

val g = Gson()

fun Any.toGson() {
    g.toJson(this)
}

fun View.visible() {
    this.visibility = VISIBLE
}

fun View.gon() {
    this.visibility = GONE
}

fun View.invisible() {
    this.visibility = INVISIBLE
}

fun Exception.print() {
    this.printStackTrace()
    (this.message + " | " + this.stackTrace[1].toString() + " | " + this.javaClass.name).log()
}

fun Any.log() {
    Log.wtf(TAG, "$this")
}

fun ImageFilterView.load(any: Any?, withCrossFade: Boolean = false) {
    Glide.with(this).load(any).placeholder(R.drawable.ic_img_loading).error(R.drawable.ic_error).apply {
        if (withCrossFade)
            transition(DrawableTransitionOptions.withCrossFade()).into(this@load)
        else
            into(this@load)
    }
}

fun ImageView.load(any: Any?, withCrossFade: Boolean = false) {
    Glide.with(this).load(any).placeholder(R.drawable.ic_img_loading).error(R.drawable.ic_error).apply {
        if (withCrossFade)
            transition(DrawableTransitionOptions.withCrossFade()).into(this@load)
        else
            into(this@load)
    }
}




