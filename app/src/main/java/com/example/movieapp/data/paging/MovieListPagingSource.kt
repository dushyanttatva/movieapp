package com.example.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.domain.model.Movie

class MovieListPagingSource(
    private val repository: MovieRepositoryImpl
): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val movies = repository.getMovieList(page)

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}