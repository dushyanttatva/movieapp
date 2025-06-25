package com.example.movieapp.data.di

import com.example.movieapp.data.network.ApiService
import com.example.movieapp.data.network.NetworkConnectionInterceptor
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MovieDataModule {
    private const val BASE_URL= "https://api.themoviedb.org/3/"
    private const val BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNjNiOGE2Y2ZmYTkxYTUwMzEyODFiNDU5M2M2NDE4ZSIsIm5iZiI6MTc1MDY3Mjk2My42ODksInN1YiI6IjY4NTkyNjQzNWNkMDMxMTdkYjUzZDA5ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.geIiMfQ63x1fYXGHPZLYY7zbL1k-KueKPS9MfPHhf6M"

    @Singleton
    @Provides
    fun provideApiService(
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): ApiService{

        val okkHttpClient = OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $BEARER_TOKEN")
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideMovieRepo(
        apiService: ApiService
    ): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }
}