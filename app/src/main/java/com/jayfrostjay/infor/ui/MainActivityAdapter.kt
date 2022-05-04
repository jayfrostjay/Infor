package com.jayfrostjay.infor.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jayfrostjay.infor.data.Contacts
import com.jayfrostjay.infor.databinding.ContactsBinding

class MainActivityAdapter: RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    var onClick: ((Contacts?) -> Unit)? = null
    var list: List<Contacts?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ContactsBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list?.get(position)?.let{
            holder.apply {
                this.onClick = this@MainActivityAdapter.onClick
                bindData(it)
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class ViewHolder(val v: ContactsBinding): RecyclerView.ViewHolder(v.root) {
        var onClick: ((Contacts?) -> Unit)? = null

        fun bindData(contacts: Contacts?){
            contacts ?: return

            v.fullName.text = "Fullname: ${contacts?.lastname}, ${contacts.firstname}"
            v.contactNumbers.text = "Contacts: ${contacts?.numbers}"
        }
    }
}