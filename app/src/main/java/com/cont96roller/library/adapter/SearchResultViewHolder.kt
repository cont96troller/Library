package com.cont96roller.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cont96roller.library.databinding.ItemSearchResultBinding
import com.cont96roller.library.model.SearchBookResultModel

class SearchResultViewHolder(
    private val binding: ItemSearchResultBinding,
    private val onItemClick: (SearchBookResultModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(searchBookResultModel: SearchBookResultModel) {
        binding.apply {

            bookModel = searchBookResultModel

            Glide.with(root.context).asBitmap().load(searchBookResultModel.thumbnail)
                .into(imgThumbnail)

            root.setOnClickListener {
                onItemClick(searchBookResultModel)
            }

        }
    }

    companion object {
        fun getViewHolder(
            parent: ViewGroup,
            itemClick: (SearchBookResultModel) -> Unit
        ): SearchResultViewHolder {
            val binding =
                ItemSearchResultBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            return SearchResultViewHolder(binding, itemClick)
        }
    }
}