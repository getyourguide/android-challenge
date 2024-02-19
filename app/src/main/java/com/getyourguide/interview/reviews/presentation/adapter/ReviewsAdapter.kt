package com.getyourguide.interview.reviews.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.getyourguide.interview.R
import com.getyourguide.interview.reviews.data.entitiy.ReviewResponse
import com.getyourguide.interview.reviews.presentation.ReviewView

class ReviewsAdapter : RecyclerView.Adapter<ReviewsViewHolder>() {

  private val items: MutableList<ReviewView> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.row_reviews, parent, false)
    return ReviewsViewHolder(view)
  }

  override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun getItemCount(): Int = items.size

  fun submitItems(items: List<ReviewView>) {
    val diffResult = DiffUtil.calculateDiff(MyDiffCallback(this.items, items))
    this.items.clear()
    this.items.addAll(items)
    diffResult.dispatchUpdatesTo(this)
  }
}

class MyDiffCallback(
  private val oldList: List<ReviewView>,
  private val newList: List<ReviewView>
) : DiffUtil.Callback() {

  override fun getOldListSize(): Int {
    return oldList.size
  }

  override fun getNewListSize(): Int {
    return newList.size
  }

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition] == newList[newItemPosition]
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition] == newList[newItemPosition]
  }
}
