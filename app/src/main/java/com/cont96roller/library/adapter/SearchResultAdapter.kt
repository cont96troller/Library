package com.cont96roller.library.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cont96roller.library.LibraryWriteActivity
import com.cont96roller.library.model.SearchBookResultModel


class SearchResultAdapter(private val onItemClick: (SearchBookResultModel) -> Unit) :
    RecyclerView.Adapter<SearchResultViewHolder>() {

//    private lateinit var binding: AdapterResultSearch
    var searchResultModel: List<SearchBookResultModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder =
        SearchResultViewHolder.getViewHolder(parent, onItemClick)

    override fun getItemCount() = searchResultModel?.size ?: 0

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
            searchResultModel?.get(position)?.also {
                holder.bind(it)
            }

        

        fun list(v: View) {

//            val intent = Intent(application, LibraryWriteActivity::class.java)
//            startActivity(intent)
//            finish()
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

//@Override
//public int getItemCount() { return(null != mDiaryList ? mDiaryList.size() : 0); }
