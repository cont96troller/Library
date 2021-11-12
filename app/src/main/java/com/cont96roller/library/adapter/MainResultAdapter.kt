package com.cont96roller.library.adapter

import android.view.ViewGroup
import android.widget.RemoteViews
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cont96roller.library.model.MainReviewResultModel
import com.cont96roller.library.room.Review

class MainResultAdapter(private val onItemClick: (Review) -> Unit) :
    RecyclerView.Adapter<MainResultViewHolder>() {

    var mainReviewResultModel: List<Review>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainResultViewHolder =
        MainResultViewHolder.getViewHolder(parent, onItemClick)


    override fun getItemCount() = mainReviewResultModel?.size ?: 0

    override fun onBindViewHolder(holder: MainResultViewHolder, position: Int) {
        mainReviewResultModel?.get(position)?.also {
            holder.bind(it)
            }
        }
    }

    @BindingAdapter("mainResult")
    fun bindMainList(recycler: RecyclerView, items: List<Review>?) {
        val adapter = recycler.adapter as MainResultAdapter?
        adapter?.let {
            it.mainReviewResultModel = items
            it.notifyDataSetChanged()
        }

    }


