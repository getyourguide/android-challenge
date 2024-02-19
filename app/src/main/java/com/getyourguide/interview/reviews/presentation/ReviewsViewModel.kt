package com.getyourguide.interview.reviews.presentation

import androidx.lifecycle.viewModelScope
import com.getyourguide.interview.base.presetation.AsyncState
import com.getyourguide.interview.base.presetation.BaseViewModel
import com.getyourguide.interview.base.presetation.Failure
import com.getyourguide.interview.base.presetation.Loading
import com.getyourguide.interview.base.presetation.Success
import com.getyourguide.interview.base.presetation.Uninitialized
import com.getyourguide.interview.reviews.domain.usecase.FetchingReviews
import com.getyourguide.interview.reviews.presentation.mapper.toReviewView
import kotlinx.coroutines.launch

class ReviewsViewModel(
    private val fetchingReviews: FetchingReviews = FetchingReviews()
) : BaseViewModel<ReviewState>(ReviewState()) {

    init {
        getReviews()
    }

    private fun getReviews() {
        setState {
            it.copy(
                reviews = Loading()
            )
        }
        viewModelScope.launch {
            try {
                val reviews = fetchingReviews()
                    .map { it.toReviewView() }
                setState {
                    it.copy(
                        reviews = Success(reviews)
                    )
                }
            } catch (e: Exception) {
                setState {
                    it.copy(
                        reviews = Failure(e)
                    )
                }
            }
        }
    }

}

data class ReviewState(
    val reviews: AsyncState<List<ReviewView>> = Uninitialized
)
