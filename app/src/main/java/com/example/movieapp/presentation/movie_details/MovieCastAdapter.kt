package com.example.movieapp.presentation.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.ItemCastBinding
import com.example.movieapp.domain.model.MovieCast

class MovieCastAdapter(
    private var castList: List<MovieCast>
): RecyclerView.Adapter<MovieCastAdapter.CastViewHolder>() {
    inner class CastViewHolder(
        private val binding: ItemCastBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(movieCast: MovieCast) {
            binding.tvCastName.text = movieCast.name

            val castImageUrl = "https://image.tmdb.org/t/p/w500${movieCast.profilePath}"
            Glide.with(binding.ivCastImage.context)
                .load(castImageUrl)
                .into(binding.ivCastImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val castItem = castList[position]
        holder.bind(castItem)
    }

    override fun getItemCount(): Int = castList.size

    fun updateList(newList: List<MovieCast>) {
        castList = newList
        notifyDataSetChanged()
    }
}