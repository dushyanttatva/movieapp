package com.example.movieapp.presentation.movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.common.Constant
import com.example.movieapp.common.hide
import com.example.movieapp.common.show
import com.example.movieapp.databinding.ActivityMovieDetailsBinding
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieCast
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var movieData: Movie
    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var movieCastAdapter: MovieCastAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpData()
        setUpCastRecyclerView()
        observeMovieCast()
    }

    private fun setUpData() {
        val intent = this.intent
        if(intent != null){
            val movieDataObj = intent.getStringExtra(Constant.movieDetailsData)
            movieData = Gson().fromJson(movieDataObj, Movie::class.java)

            viewModel.fetchMovieCast(movieData.id)

            binding.tvMovieName.text = movieData.title
            binding.tvDescription.text = movieData.overview
            "${getStringDynamic(R.string.release_date)} ${movieData.releaseDate}".also { binding.tvReleaseDate.text = it }

            val posterUrl = "https://image.tmdb.org/t/p/w500${movieData.posterPath}"
            Glide.with(binding.ivPosterImage.context)
                .load(posterUrl)
                .into(binding.ivPosterImage)
        }
    }

    private fun setUpCastRecyclerView() {
        movieCastAdapter = MovieCastAdapter(emptyList())
        binding.movieCastRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            adapter = movieCastAdapter
        }
    }

    private fun observeMovieCast() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.movieCast.collect {
                    when{
                        it.isLoading -> {
                            binding.progressBar.show()
                        }
                        it.error.isNotEmpty() -> {
                            binding.progressBar.hide()
                        }
                        it.data?.isNotEmpty() == true -> {
                            binding.progressBar.hide()
                            movieCastAdapter.updateList(it.data)
                            binding.movieCastRecyclerView.adapter = movieCastAdapter
                        }
                    }
                }
            }
        }
    }

    private fun getStringDynamic(id: Int): String {
        return resources.getString(id)
    }
}