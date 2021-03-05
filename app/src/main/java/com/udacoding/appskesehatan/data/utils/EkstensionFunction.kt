package com.udacoding.appskesehatan.data.utils

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.udacoding.appskesehatan.R
import com.udacoding.appskesehatan.data.utils.code.CAMERA_CODE
import com.udacoding.appskesehatan.data.utils.code.GALLERY_CODE
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

fun Activity.initPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(
            arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_NETWORK_STATE
            ), 1
        )
    }
}

fun Activity.persistImage(bitmap: Bitmap, name: String): String {
    val filesDir = filesDir
    val imageFile = File(filesDir, name + ".png")

    val image_path = imageFile.path

    val os: OutputStream?
    try {
        os = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
        os.flush()
        os.close()
    } catch (e: Exception) {
        Log.e(javaClass.simpleName, getString(R.string.error_writing_bitmap), e)
    }

    return image_path ?: ""
}

object code {
    const val CAMERA_CODE = 0
    const val GALLERY_CODE = 1
}