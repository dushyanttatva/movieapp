package com.example.movieapp.presentation.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.domain.model.Movie

class MovieListAdapter(
    private val onItemClick: (Movie) -> Unit
): PagingDataAdapter<Movie, MovieListAdapter.MovieListViewHolder>(DifferCallBack) {

    object DifferCallBack: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MovieListViewHolder(
        private val itemBinding: ItemMovieBinding
    ): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: Movie) {
            itemBinding.tvMovieName.text = movie.title
            itemBinding.tvDescription.text = movie.overview

            val posterUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
            Glide.with(itemBinding.ivPosterImage.context)
                .load(posterUrl)
                .into(itemBinding.ivPosterImage)

            itemBinding.root.setOnClickListener {
                onItemClick(movie)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }
}