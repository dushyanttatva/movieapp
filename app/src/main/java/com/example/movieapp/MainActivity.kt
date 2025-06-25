package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.common.hide
import com.example.movieapp.common.show
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.presentation.movie_list.MovieListAdapter
import com.example.movieapp.presentation.movie_list.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
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

        viewModel.fetchMovieList()
        setupRecyclerView()
        observeMovieList()
    }

    private fun setupRecyclerView() {
        movieListAdapter = MovieListAdapter(
            emptyList(),
            onItemClick = {movie ->  goToMovieDetail(movie)}
        )
        binding.movieListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieListAdapter
        }
    }

    private fun observeMovieList() {
        lifecycleScope.launch {
            viewModel.list.collect {
                when{
                    it.isLoading -> {
                        binding.progressBar.show()
                    }
                    it.error.isNotEmpty() -> {
                        binding.progressBar.hide()
                    }
                    it.data?.isNotEmpty() == true -> {
                        binding.progressBar.hide()
                        movieListAdapter.updateMovieList(it.data!!)
                        binding.movieListRecyclerView.adapter = movieListAdapter
                    }
                }
            }
        }
    }

    private fun goToMovieDetail(movie: Movie?) {}

}