package com.example.sampleapp.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.domain.model.Brochure

class BrochureAdapter : RecyclerView.Adapter<BrochureViewHolder>() {
    private var items = listOf<Brochure>()

    fun setItems(brochures: List<Brochure>) {
        this.items = brochures
        notifyDataSetChanged()
    }

    fun isPremium(position: Int): Boolean {
        return items[position].isPremium
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrochureViewHolder {
        return BrochureViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: BrochureViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}