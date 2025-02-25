package com.example.loopmovieapp.di

import com.example.loopmovieapp.com.example.loopmovieapp.data.UserRepository
import com.example.loopmovieapp.com.example.loopmovieapp.ui.screens.movieDetails.MovieDetailsViewModel
import com.example.loopmovieapp.data.MovieRepository
import com.example.loopmovieapp.ui.screens.allMovies.AllMoviesViewModel
import com.example.loopmovieapp.ui.screens.home.HomeViewModel
import com.example.loopmovieapp.ui.screens.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { MovieRepository(get()) }
    single { UserRepository() }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { AllMoviesViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}

