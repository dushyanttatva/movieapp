package com.example.movieapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.common.Constant
import com.example.movieapp.common.hide
import com.example.movieapp.common.show
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.presentation.movie_details.MovieDetailsActivity
import com.example.movieapp.presentation.movie_list.MovieListAdapter
import com.example.movieapp.presentation.movie_list.MovieListViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var movieListAdapter: MovieListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeMovieList()
    }

    private fun setupRecyclerView() {
        movieListAdapter = MovieListAdapter(
            onItemClick = {movie ->  goToMovieDetail(movie)}
        )
        binding.movieListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            adapter = movieListAdapter
        }
        lifecycleScope.launch {
            movieListAdapter.loadStateFlow.collect{ loadStates ->
                when {
                    loadStates.refresh is LoadState.Loading -> {
                        binding.progressBar.show()
                    }
                    loadStates.refresh is LoadState.Error -> {
                        binding.progressBar.hide()
                    }
                    loadStates.append is LoadState.Error -> {
                        binding.progressBar.hide()
                    }
                    else -> {
                        binding.progressBar.hide()
                    }
                }
            }
        }
    }

    private fun observeMovieList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movies.collectLatest { pagingData ->
                    movieListAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun goToMovieDetail(movie: Movie?) {
        if (movie != null) {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            val sJson = Gson().toJson(movie)
            intent.putExtra(Constant.movieDetailsData, sJson)
            startActivity(intent)
        }
    }

}