package com.example.loopmovieapp.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loopmovieapp.com.example.loopmovieapp.data.UserRepository
import com.example.loopmovieapp.com.example.loopmovieapp.domain.User
import com.example.loopmovieapp.data.MovieRepository
import com.example.loopmovieapp.domain.Movie

class HomeViewModel(
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository
) :
    ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _bookmarkedMovies = MutableLiveData<List<Movie>>()
    val bookmarkedMovies: LiveData<List<Movie>> get() = _bookmarkedMovies

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    init {
        loadUser()
    }

    private fun loadUser() {
        _user.value = userRepository.getUser()
    }

    fun loadMovies() {
        val moviesList = movieRepository.getMovies(true)
        _movies.postValue(moviesList)
    }

    fun loadBookmarkedMovies() {
        val bookmarkedMoviesList = movieRepository.getMovies()
        _bookmarkedMovies.postValue(bookmarkedMoviesList)
    }
}