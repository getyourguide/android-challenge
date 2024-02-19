package com.getyourguide.interview.reviews.presentation

data class ReviewView(
    val id: Long,
    val author: AuthorView,
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

data class AuthorView(
    val fullName: String?,
    val country: String?,
    val photo: String?
)