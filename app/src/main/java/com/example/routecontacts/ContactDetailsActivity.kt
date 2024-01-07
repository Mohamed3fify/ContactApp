package com.example.routecontacts

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.routecontacts.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var detailsBinding:ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsBinding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(detailsBinding.root)
       val contacts =  intent.parcelable<ContactData>("contact")
        contacts?.let { contact ->
            detailsBinding.contactDetailsImg.setImageResource(contact.profileImg)
            detailsBinding.contactNameDetails.text = contact.profileName
            detailsBinding.contactNumberDetails.text = contact.profilePhone
            detailsBinding.contactDescriptionDetails.text = contact.description
        }

        detailsBinding.backBtn.setOnClickListener {
            onBackPressed()
        }

    }
    private inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }
}