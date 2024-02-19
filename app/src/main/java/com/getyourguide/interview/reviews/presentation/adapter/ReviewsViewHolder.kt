package com.getyourguide.interview.reviews.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.getyourguide.interview.R
import com.getyourguide.interview.reviews.data.entitiy.ReviewResponse
import com.getyourguide.interview.databinding.RowReviewsBinding
import com.getyourguide.interview.reviews.presentation.ReviewView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReviewsViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

  private val binding = RowReviewsBinding.bind(containerView)


  fun bind(item: ReviewView) {

    binding.dateText.text = item.created
    binding.ratingBar.rating = item.rating ?: 0f
    binding.message.text = item.message
    binding.author.text = item.author.fullName + "-" + item.author.country
    binding.image.load(item.author.photo) {
      fallback(R.drawable.ic_launcher_background)
      crossfade(true)
    }
  }
}
