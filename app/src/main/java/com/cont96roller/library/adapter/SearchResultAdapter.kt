package com.cont96roller.library.adapter

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cont96roller.library.model.SearchBookResultModel


class SearchResultAdapter(private val onItemClick: (SearchBookResultModel) -> Unit) :
    RecyclerView.Adapter<SearchResultViewHolder>() {
    var searchResultModel: List<SearchBookResultModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder =
        SearchResultViewHolder.getViewHolder(parent, onItemClick)

    override fun getItemCount() = searchResultModel?.size ?: 0

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
            searchResultModel?.get(position)?.also {
                holder.bind(it)
            }
        }
    }
@BindingAdapter("searchResult")
fun bindSearchList(recycler: RecyclerView, items: List<SearchBookResultModel>?) {
    val adapter = recycler.adapter as SearchResultAdapter?
    adapter?.let {
        it.searchResultModel = items
        it.notifyDataSetChanged()

    }
}
