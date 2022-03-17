package com.example.firstproject.activities.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.activities.fragments.viewholders.SubcategoryViewHolder
import com.example.firstproject.data.remote.Subcategory
import com.example.firstproject.databinding.ViewHolderSubCategoryBinding

class ViewPagerAdapter(val list:List<Subcategory>) :RecyclerView.Adapter<SubcategoryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding=ViewHolderSubCategoryBinding.inflate(layoutInflater,parent, false)
        return SubcategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}