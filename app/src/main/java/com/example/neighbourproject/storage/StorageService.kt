package com.example.neighbourproject.storage

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView

interface StorageService {
    fun writeSmallImageStorage(filename: String, bitmap: Bitmap): String

    fun loadSmallImage(context: Context, url: String, view: ImageView)
}