package com.example.routecontacts

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class ContactData(
    val profileImg: Int,
    val profileName: String,
    val profilePhone: String, // when i make it Int , the app doesn't read the first 0 on the left hand side
    val description: String

) : Parcelable
