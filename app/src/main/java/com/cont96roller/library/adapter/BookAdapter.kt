package com.cont96roller.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cont96roller.library.databinding.ItemBookBinding
import com.cont96roller.library.model.KakaoBookModel

class BookAdapter: ListAdapter<KakaoBookModel, BookAdapter.BookItemViewHolder>(diffUtil) {

    inner class BookItemViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(bookModel: KakaoBookModel) {
//            binding.titleTextView.text = bookModel.documents.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<KakaoBookModel>() {
            override fun areItemsTheSame(oldItem: KakaoBookModel, newItem: KakaoBookModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: KakaoBookModel, newItem: KakaoBookModel): Boolean {
                return oldItem.documents == newItem.documents
            }

        }
    }
}