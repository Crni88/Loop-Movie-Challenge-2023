package com.example.loopmovieapp.domain

data class Movie(
    val budget: Int,
    val cast: List<Cast>,
    val director: Director,
    val genres: List<String>,
    val id: Int,
    val language: String,
    val overview: String,
    val posterUrl: String,
    val rating: Double,
    val releaseDate: String,
    val revenue: Int,
    val reviews: Int,
    val runtime: Int,
    val title: String,
    val formattedBudget: String = "",
    val formatedRevenue: String = "",
    val formattedRuntime: String = "",
    var isBookmarked: Boolean = false,
    var isStaffPick: Boolean = false
)