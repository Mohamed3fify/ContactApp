package com.example.routecontacts

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class ContactData(
    val profileImg: Int,
    val profileName: String,
    val profilePhone: String,
    val description: String
) : Parcelable
