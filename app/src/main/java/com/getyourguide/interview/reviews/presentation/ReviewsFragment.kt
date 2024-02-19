package com.getyourguide.interview.reviews.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.getyourguide.interview.base.presetation.Failure
import com.getyourguide.interview.base.presetation.Loading
import com.getyourguide.interview.base.presetation.Success
import com.getyourguide.interview.base.presetation.getErrorMessage
import com.getyourguide.interview.databinding.FragmentReviewsBinding
import com.getyourguide.interview.reviews.presentation.adapter.ReviewsAdapter
import kotlinx.coroutines.launch
import java.lang.Exception

class ReviewsFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment = ReviewsFragment()
    }

    private var _binding: FragmentReviewsBinding? = null
    private val binding: FragmentReviewsBinding
        get() = _binding!!

    private val adapter = ReviewsAdapter()
    private lateinit var reviewsViewModel: ReviewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        reviewsViewModel = ViewModelProvider(this)[ReviewsViewModel::class.java]
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                reviewsViewModel.state.collect {
                    val reviews = it.reviews
                    onLoading(reviews is Loading)
                    when (reviews) {
                        is Success -> onSuccess(reviews())
                        is Failure -> onFailure(reviews.e)
                        else -> {
                            // do nothing
                        }
                    }
                }
            }
        }
    }

    private fun onLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    private fun onSuccess(reviews: List<ReviewView>) {
        adapter.submitItems(reviews)
        binding.progress.visibility = View.GONE

    }

    private fun onFailure(e: Exception) {
        Toast.makeText(
            requireContext(),
            requireContext().getErrorMessage(e),
            Toast.LENGTH_LONG
        ).show()
    }
}
