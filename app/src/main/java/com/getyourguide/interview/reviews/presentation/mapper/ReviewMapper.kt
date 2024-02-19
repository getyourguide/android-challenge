package com.getyourguide.interview.reviews.presentation.mapper

import com.getyourguide.interview.reviews.data.entitiy.AuthorEntity
import com.getyourguide.interview.reviews.domain.Author
import com.getyourguide.interview.reviews.domain.Review
import com.getyourguide.interview.reviews.presentation.AuthorView
import com.getyourguide.interview.reviews.presentation.ReviewView
import com.getyourguide.interview.reviews.presentation.utils.formatDate

fun Review.toReviewView(): ReviewView {
    return ReviewView(
        id,
        author.toAuthorView(),
        title,
        message,
        enjoyment,
        isAnonymous,
        rating,
        created.formatDate(),
        optionId,
        activityId,
        language,
        travelerType
    )
}

fun Author.toAuthorView(): AuthorView {
    return AuthorView(
        fullName,
        country,
        photo
    )
}