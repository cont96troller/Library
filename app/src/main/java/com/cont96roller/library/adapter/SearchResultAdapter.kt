package com.cont96roller.library.adapter

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cont96roller.library.model.SearchBookModel


class SearchResultAdapter(private val onItemClick: (SearchBookModel) -> Unit) :
    RecyclerView.Adapter<SearchResultViewHolder>() {
    var searchResult: List<SearchBookModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder =
        SearchResultViewHolder.getViewHolder(parent, onItemClick)

    override fun getItemCount() = searchResult?.size ?: 0

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
            searchResult?.get(position)?.also {
                holder.bind(it)
            }
        }
    }
@BindingAdapter("searchResult")
fun bindSearchList(recycler: RecyclerView, items: List<SearchBookModel>?) {
    val adapter = recycler.adapter as SearchResultAdapter?
    adapter?.let {
        it.searchResult = items
        it.notifyDataSetChanged()

    }
}
