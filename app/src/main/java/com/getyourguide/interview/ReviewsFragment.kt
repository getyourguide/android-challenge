package com.getyourguide.interview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReviewsFragment : Fragment() {

  companion object {
    fun newInstance(): Fragment = ReviewsFragment()
  }

  private val api: ReviewsApi = RetrofitBuilder.build(ReviewsApi::class.java)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        var data by remember { mutableStateOf<ReviewResponse?>(null) }
        GlobalScope.launch {
          val response = api.getReviews()
          data = response
        }

        data?.let {
          Column(
            Modifier.verticalScroll(rememberScrollState())
          ) {
            it.reviews.forEachIndexed { index, it ->
              ReviewItem(it)
            }
          }
        }

        if (data == null) {
          Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
              modifier = Modifier.align(Alignment.Center),
              color = Colors.GUIDING_RED
            )
          }
        }

      }
    }
  }


}

@Composable
fun ReviewItem(review: ReviewResponse.ReviewsDto) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
  ) {
    val rating = remember { review.rating ?: 0f }
    val message = remember { review.message ?: "" }
    val authorName = remember { review.author.fullName ?: "" }
    val authorCountry = remember { review.author.country ?: "" }
    val authorPhoto = remember { review.author.photo ?: "" }
    val dateText = LocalDateTime.parse(
      review.created,
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz")
    ).format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))

    Text(
      text = dateText,
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.labelPrimary,
    )

    Spacer(modifier = Modifier.height(10.dp))

    RatingBar(rating = rating)

    Spacer(modifier = Modifier.height(6.dp))

    Text(
      text = message,
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.labelPrimary
    )

    Spacer(modifier = Modifier.height(6.dp))

    Row(
      modifier = Modifier
        .padding(bottom = 10.dp)
    ) {

      AsyncImage(
        model = authorPhoto,
        contentDescription = null,
        modifier = Modifier
          .height(40.dp)
          .width(40.dp)
          .clip(CircleShape)
          .background(Colors.GUIDING_RED)
          .align(Alignment.CenterVertically)
      )
      Spacer(modifier = Modifier.width(6.dp))

      Column(
        modifier = Modifier.align(Alignment.CenterVertically)
      ) {
        Text(
          text = stringResource(id = R.string.reviewed_by),
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.labelPrimary,
        )
        Text(
          text = "${authorName} - ${authorCountry}",
          style = MaterialTheme.typography.bodyMedium,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          color = MaterialTheme.colorScheme.labelPrimary,
        )
      }
    }

    Spacer(
      modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Color.DarkGray)
    )
  }
}
