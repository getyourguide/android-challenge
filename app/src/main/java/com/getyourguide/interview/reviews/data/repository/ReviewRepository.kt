package com.getyourguide.interview.reviews.data.repository

import com.getyourguide.interview.reviews.data.entitiy.mapper.toReview
import com.getyourguide.interview.reviews.data.repository.datasource.ReviewRemoteDataSource
import com.getyourguide.interview.reviews.data.repository.datasource.ReviewRemoteDataSourceImpl
import com.getyourguide.interview.reviews.domain.repository.ReviewRepository

class ReviewRepositoryImpl(
    private val reviewRemoteDataSource: ReviewRemoteDataSource = ReviewRemoteDataSourceImpl()
): ReviewRepository {
    override suspend fun getReviews() = reviewRemoteDataSource
        .getReviews()
        .reviews.map {
            it.toReview()
        }
}
