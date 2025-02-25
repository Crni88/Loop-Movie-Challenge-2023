package com.example.loopmovieapp.data

import android.content.Context
import android.util.Log
import com.example.loopmovieapp.domain.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieRepository(private val context: Context) {

    private var movieList: List<Movie> = emptyList()

    init {
        val movies = getMoviesFromAssets("movies.json")
        val staffPicks = getMoviesFromAssets("staff_picks.json")

        staffPicks.forEach { it.isStaffPick = true }

        val staffPickIds = staffPicks.map { it.id }.toSet()
        movies.forEach { movie ->
            if (movie.id in staffPickIds) {
                movie.isStaffPick = true
            }
        }

        movieList = (movies + staffPicks)
            .groupBy { it.id }
            .map { (_, movieGroup) ->
                movieGroup.first().apply { isStaffPick = movieGroup.any { it.isStaffPick } }
            }
    }


    fun getMovies(isStaffPick: Boolean = false): List<Movie> {
        return if (isStaffPick) {
            movieList.filter { it.isStaffPick }
        } else {
            movieList
        }
    }

    private fun getMoviesFromAssets(fileName: String): List<Movie> {
        val json = readJsonFromAssets(fileName)
        return if (json != null) {
            movieList = parseMovies(json)
            movieList
        } else {
            emptyList()
        }
    }

    private fun readJsonFromAssets(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error reading $fileName: ${e.message}")
            null
        }
    }

    private fun parseMovies(json: String): List<Movie> {
        val listType = object : TypeToken<List<Movie>>() {}.type
        return Gson().fromJson(json, listType)
    }

    fun getMovieById(id: Int): Movie? {
        return movieList.find { it.id == id }
    }

    fun setMovieBookmark(movieId: Int, bookmarked: Boolean) {
        val movie = movieList.find { it.id == movieId } ?: return
        movie.isBookmarked = bookmarked
    }
}
