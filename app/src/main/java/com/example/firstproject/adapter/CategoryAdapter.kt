package com.example.firstproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.viewholders.CategoryViewHolder
import com.example.firstproject.data.remote.Category
import com.example.firstproject.databinding.ViewHolderCategoryBinding

class CategoryAdapter (val categoryList: List<Category>): RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCategoryBinding.inflate(layoutInflater,parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category=categoryList[position] as Category
        holder.bind(category)

        holder.itemView.setOnClickListener {
            if(this::categorySelectedListener.isInitialized) {
                categorySelectedListener(category, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    private lateinit var categorySelectedListener: (Category, Int) -> Unit

    fun setOnCategorySelectedListener(listner: (Category, Int) -> Unit) {
        categorySelectedListener = listner
    }

}