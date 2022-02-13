package com.example.sampleapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ItemBrochureBinding
import com.example.sampleapp.domain.model.Brochure

class BrochureViewHolder(private val binding: ItemBrochureBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): BrochureViewHolder {
            return BrochureViewHolder(
                ItemBrochureBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(brochure: Brochure) {
        if (brochure.image.isNullOrEmpty()) {
            Glide.with(itemView)
                .load(R.drawable.ic_place_holder)
                .transform(CenterCrop(), RoundedCorners(26))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivBrochure)
        } else {
            Glide.with(itemView)
                .load(brochure.image)
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_place_holder)
                .transform(CenterCrop(), RoundedCorners(26))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivBrochure)
        }
        binding.tvRetailerName.text = brochure.retailerName
    }
}