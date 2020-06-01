package com.rahul.moviesearch.features.movie_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rahul.moviesearch.R
import com.rahul.moviesearch.databinding.ActivityMovieDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsActivity : AppCompatActivity() {
    private val viewModel by viewModel<MovieDetailsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMovieDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.viewModel = viewModel
        fetchMovieDetails()
    }

    fun fetchMovieDetails(){
        val movieId = intent?.extras?.getString("movieId")
        viewModel.fetchMovieDetails(movieId)
    }


}