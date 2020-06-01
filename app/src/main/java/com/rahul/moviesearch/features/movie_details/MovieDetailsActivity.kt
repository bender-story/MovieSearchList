package com.rahul.moviesearch.features.movie_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.rahul.moviesearch.R
import com.rahul.moviesearch.component.DialogExt
import com.rahul.moviesearch.databinding.ActivityMovieDetailsBinding
import com.rahul.moviesearch.features.movie_details.viewmodel.MovieDetailsViewModel
import com.rahul.moviesearch.features.search.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsActivity : AppCompatActivity() {
    private val viewModel by viewModel<MovieDetailsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMovieDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.viewModel = viewModel
        initObservers()
        fetchMovieDetails()
    }

    /**
     * observer to check live data changes
     */
    private fun initObservers() {
        viewModel.error.observe(this, Observer {
            DialogExt(this).buildDialog(getString(R.string.error)) {
                fetchMovieDetails()
            }
        })
    }

    private fun fetchMovieDetails(){
        val movieId = intent?.extras?.getString("movieId")
        viewModel.fetchMovieDetails(movieId)
    }


}