package com.example.firstproject.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.data.remote.Addresse
import com.example.firstproject.databinding.ViewHolderAddressBinding

class AddressViewHolder(val binding: ViewHolderAddressBinding) :RecyclerView.ViewHolder(binding.root){
    fun bind(address: Addresse){
        binding.tvTitle.text=address.title
        binding.tvAddress.text=address.address
    }
}