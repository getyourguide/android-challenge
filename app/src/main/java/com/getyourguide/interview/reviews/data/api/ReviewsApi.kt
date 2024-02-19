package com.getyourguide.interview.reviews.data.api

import com.getyourguide.interview.reviews.data.entitiy.ReviewResponse
import retrofit2.http.GET

interface ReviewsApi {

  @GET("activities/141648/reviews?offset=0&limit=60")
  suspend fun getReviews(): ReviewResponse

}
