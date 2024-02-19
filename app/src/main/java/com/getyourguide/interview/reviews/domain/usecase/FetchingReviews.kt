package com.getyourguide.interview.reviews.domain.usecase

import com.getyourguide.interview.reviews.data.repository.ReviewRepositoryImpl
import com.getyourguide.interview.reviews.domain.repository.ReviewRepository

class FetchingReviews(
    private val reviewRepository: ReviewRepository = ReviewRepositoryImpl()
) {
    suspend operator fun invoke() = reviewRepository.getReviews()
}
