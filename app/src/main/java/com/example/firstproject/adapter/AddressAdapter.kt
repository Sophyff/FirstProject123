package com.example.firstproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.data.remote.Addresse
import com.example.firstproject.databinding.ViewHolderAddressBinding
import com.example.firstproject.viewholders.AddressViewHolder

class AddressAdapter(val list:List<Addresse>):RecyclerView.Adapter<AddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding=ViewHolderAddressBinding.inflate(layoutInflater, parent, false)
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address=list[position]
        holder.bind(address)

        holder.itemView.setOnClickListener {
            if(this::itemSelectedListener.isInitialized) {
                itemSelectedListener(address, position)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    private lateinit var itemSelectedListener: (Addresse, Int) -> Unit

    fun setOnItemSelectedListener(listner: (Addresse, Int) -> Unit) {
        itemSelectedListener = listner
    }


}