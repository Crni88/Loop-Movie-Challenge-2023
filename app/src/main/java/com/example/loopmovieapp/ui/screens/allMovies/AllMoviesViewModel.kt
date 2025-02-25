package com.example.loopmovieapp.ui.screens.allMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loopmovieapp.data.MovieRepository
import com.example.loopmovieapp.domain.Movie

class AllMoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _filteredMovies = MutableLiveData<List<Movie>>()
    val filteredMovies: LiveData<List<Movie>> get() = _filteredMovies

    fun loadMovies() {
        val moviesList = movieRepository.getMovies()
        _movies.postValue(moviesList)
        _filteredMovies.postValue(moviesList)
    }

    fun filterMovies(query: String) {
        val queryLowerCase = query.lowercase()
        val filteredList = _movies.value?.filter {
            it.title.lowercase().contains(queryLowerCase)
        } ?: emptyList()
        _filteredMovies.postValue(filteredList)
    }
}
