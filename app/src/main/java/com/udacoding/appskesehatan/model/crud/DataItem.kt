package com.udacoding.appskesehatan.model.crud

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(
    val id: Int? = null,
    val user_id: Int? = null,
    val judul: String? = null,
    val deskripsi: String? = null,
    val image: String? = null
): Parcelable