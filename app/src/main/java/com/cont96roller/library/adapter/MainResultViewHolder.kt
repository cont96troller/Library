package com.cont96roller.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cont96roller.library.databinding.ItemMainResultBinding
import com.cont96roller.library.model.MainReviewResultModel
import com.cont96roller.library.room.Review

class MainResultViewHolder(
    private val binding: ItemMainResultBinding,
    private val onItemClick: (Review) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mainReviewResultModel: Review) {
        binding.apply {

            reviewModel = mainReviewResultModel

            Glide.with(root.context).asBitmap().load(mainReviewResultModel.thumbnail)
                .into(imgThumbnail)

            root.setOnClickListener {
                onItemClick(mainReviewResultModel)
            }
        }
    }

    companion object {
        fun getViewHolder(
            parent: ViewGroup,
            itemClick: (Review) -> Unit
        ): MainResultViewHolder {
            val binding =
                ItemMainResultBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            return MainResultViewHolder(binding, itemClick)
        }
    }
}