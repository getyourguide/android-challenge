package com.getyourguide.interview.reviews.data.entitiy.mapper

import com.getyourguide.interview.reviews.data.entitiy.AuthorEntity
import com.getyourguide.interview.reviews.data.entitiy.ReviewEntity
import com.getyourguide.interview.reviews.domain.Author
import com.getyourguide.interview.reviews.domain.Review

fun ReviewEntity.toReview(): Review {
    return Review(
        id,
        author.toAuthor(),
        title,
        message,
        enjoyment,
        isAnonymous,
        rating,
        created,
        optionId,
        activityId,
        language,
        travelerType
    )
}

fun AuthorEntity.toAuthor(): Author {
    return Author(
        fullName,
        country,
        photo
    )
}