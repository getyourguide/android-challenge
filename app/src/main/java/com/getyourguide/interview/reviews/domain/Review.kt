package com.getyourguide.interview.reviews.domain


data class Review(
    val id: Long,
    val author: Author,
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

data class Author(
    val fullName: String?,
    val country: String?,
    val photo: String?
)