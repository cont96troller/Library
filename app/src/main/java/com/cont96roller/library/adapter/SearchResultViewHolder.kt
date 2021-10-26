package com.cont96roller.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cont96roller.library.databinding.ItemSearchResultBinding

class SearchResultViewHolder (
    private val binding: ItemSearchResultBinding,
    private val onItemClick: (com.cont96roller.library.model.SearchBookModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(searchResultModel: com.cont96roller.library.model.SearchBookModel) {
        binding.apply {
            txtAuthor.text = searchResultModel.author
            txtTitle.text = searchResultModel.title
            Glide.with(root.context).asBitmap().load(searchResultModel.thumbnailUrl)
                .into(imgThumbnail)
            root.setOnClickListener {
                onItemClick(searchResultModel)
            }
        }
    }

    companion object {
        fun getViewHolder(
            parent: ViewGroup,
            itemClick: (com.cont96roller.library.model.SearchBookModel) -> Unit
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