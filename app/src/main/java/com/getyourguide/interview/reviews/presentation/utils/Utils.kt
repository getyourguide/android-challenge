package com.getyourguide.interview.reviews.presentation.utils

import com.getyourguide.interview.reviews.presentation.const.SERVER_DATE_FORMAT
import com.getyourguide.interview.reviews.presentation.const.UI_DATE_FORMAT
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.formatDate(
    fromFormat: String = SERVER_DATE_FORMAT,
    toFormat: String = UI_DATE_FORMAT
): String {
    return LocalDateTime.parse(
        this,
        DateTimeFormatter.ofPattern(fromFormat)
    ).format(DateTimeFormatter.ofPattern(toFormat))
}