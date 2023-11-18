package com.example.petproject.ui.utils

import android.content.Context

fun getSize(context: Context): Float {
    val displayMetrics = context.resources.displayMetrics
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    return dpWidth
}