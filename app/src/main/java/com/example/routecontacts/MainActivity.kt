package com.example.routecontacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.routecontacts.databinding.ActivityMainBinding
import com.example.routecontacts.databinding.AddContactBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mainContactBinding: ActivityMainBinding
    private val contactList: MutableList<ContactData> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var addContactBinding: AddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainContactBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainContactBinding.root)

        mainContactBinding.addBtn.setOnClickListener {
            addContactBinding = AddContactBinding.inflate(layoutInflater)
            setContentView(addContactBinding.root)
            addContacts()
        }
        initRecycleView()
    }

    private fun initRecycleView() {
        contactAdapter = ContactAdapter(contactList)
        recyclerView = mainContactBinding.recycleContact
        contactAdapter.onContactClickListener = ContactAdapter.OnContactClickListener{contactDetails ->
            navigateToContactDetailsActivity(contactDetails)   }
        recyclerView.adapter = contactAdapter
    }

    private fun navigateToContactDetailsActivity(contactDetails: ContactData) {
        val intent = Intent(this , ContactDetailsActivity::class.java)
        intent.putExtra("contact" , contactDetails)
        startActivity(intent)
    }

    private fun addContacts() {
        addContactBinding.save.setOnClickListener {
            val name = addContactBinding.additionNameText.text.toString()
            val phoneNumber = addContactBinding.additionPhoneNumber.text.toString()
            val description = addContactBinding.additionDescription.text.toString()

            if (name.length >= 3 && phoneNumber.length == 11) {
                val contactItem = ContactData(profileImg = R.drawable.contact_img, name, phoneNumber, description)
                // when i convert phoneNumber.toInt() , it doesn't read the first 0 on left hand side
                contactList.add(contactItem)

                // tell the adapter that the data has changed
                 contactAdapter.notifyDataSetChanged()
                 mainContactBinding.root.visibility = View.VISIBLE
                 setContentView(mainContactBinding.root)

            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

