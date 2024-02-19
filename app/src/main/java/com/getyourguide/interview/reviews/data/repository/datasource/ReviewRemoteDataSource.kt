package com.getyourguide.interview.reviews.data.repository.datasource

import com.getyourguide.interview.base.data.RetrofitBuilder
import com.getyourguide.interview.base.data.handleHttpException
import com.getyourguide.interview.reviews.data.entitiy.ReviewResponse
import com.getyourguide.interview.reviews.data.api.ReviewsApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface ReviewRemoteDataSource {
    suspend fun getReviews(): ReviewResponse
}
class ReviewRemoteDataSourceImpl(
    private val reviewsApi: ReviewsApi = RetrofitBuilder.provideReviewApis(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ReviewRemoteDataSource {

    override suspend fun getReviews(): ReviewResponse {
        return withContext(dispatcher) {
            try {
                reviewsApi.getReviews()
            } catch (e: Exception) {
                throw e.handleHttpException()
            }
        }
    }
}
