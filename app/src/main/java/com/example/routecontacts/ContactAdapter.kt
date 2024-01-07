package com.example.routecontacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contactList: List<ContactData>?) :
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImg: ImageView = itemView.findViewById(R.id.contact_img)
         val profileName: TextView = itemView.findViewById(R.id.contact_name)
         val profilePhone: TextView = itemView.findViewById(R.id.contact_phone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contacts_data, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = contactList?.size ?: 0


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contactList?.get(position)
        contact?.profileImg?.let { holder.profileImg.setImageResource(it) }
        contact?.profileName?.let { holder.profileName.text = it }
        contact?.profilePhone?.let { holder.profilePhone.text = it.toString() }
        if (onContactClickListener !=null){
            holder.itemView.setOnClickListener {
                if (contact != null) {
                    onContactClickListener?.onContactClick(contact)
                }
            }
        }

    }
     var onContactClickListener :OnContactClickListener?=null

    fun interface OnContactClickListener{
        fun onContactClick(contactData: ContactData)
    }
}