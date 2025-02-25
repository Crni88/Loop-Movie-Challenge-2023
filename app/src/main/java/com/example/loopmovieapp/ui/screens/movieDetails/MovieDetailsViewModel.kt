package com.example.loopmovieapp.com.example.loopmovieapp.ui.screens.movieDetails

import androidx.lifecycle.ViewModel
import com.example.loopmovieapp.com.example.loopmovieapp.util.convertRuntimeToHoursAndMinutes
import com.example.loopmovieapp.com.example.loopmovieapp.util.formatDate
import com.example.loopmovieapp.com.example.loopmovieapp.util.formatMoney
import com.example.loopmovieapp.com.example.loopmovieapp.util.getLanguageFullName
import com.example.loopmovieapp.com.example.loopmovieapp.util.roundToTwoDecimals
import com.example.loopmovieapp.data.MovieRepository
import com.example.loopmovieapp.domain.Movie

class MovieDetailsViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun findMovieById(id: Int): Movie? {
        return movieRepository.getMovieById(id)?.let { movie ->
            movie.copy(
                releaseDate = formatDate(movie.releaseDate),
                rating = roundToTwoDecimals(movie.rating),
                formattedBudget = formatMoney(movie.budget),
                formatedRevenue = formatMoney(movie.revenue),
                language = getLanguageFullName(movie.language),
                formattedRuntime = convertRuntimeToHoursAndMinutes(movie.runtime)
            )
        }
    }

    fun setMovieBookmark(movieId: Int, isBookmarked: Boolean) {
        movieRepository.setMovieBookmark(movieId, isBookmarked)
    }
}
