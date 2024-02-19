package com.getyourguide.interview.reviews.domain.repository

import com.getyourguide.interview.reviews.domain.Review

interface ReviewRepository {
    suspend fun getReviews() : List<Review>
}
