package com.getyourguide.interview.reviews.data.entitiy

import com.getyourguide.interview.reviews.domain.Author
import com.getyourguide.interview.reviews.domain.Review
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewResponse(
    val reviews: List<ReviewEntity>,
    val totalCount: Int,
    val averageRating: Float,
)

@JsonClass(generateAdapter = true)
data class ReviewEntity(
    val id: Long,
    val author: AuthorEntity,
    val title: String?,
    val message: String?,
    val enjoyment: String?,
    val isAnonymous: Boolean?,
    val rating: Float?,
    val created: String,
    val optionId: Int?,
    val activityId: Int,
    val language: String?,
    val travelerType: String?
)

@JsonClass(generateAdapter = true)
data class AuthorEntity(
    val fullName: String?,
    val country: String?,
    val photo: String?
)
