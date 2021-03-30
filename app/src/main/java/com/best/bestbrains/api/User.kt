package com.best.bestbrains.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// from Json
@Parcelize
data class User(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
): Parcelable {
    val fullName: String
       get() = "${"$first_name $last_name"}"
}