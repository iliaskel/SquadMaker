package com.example.squadmaker.model.remotedatasource.retrofit

data class DetailedMovie(
    val id: Int,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val recommendations: Recommendations,
    val release_date: String,
    val similar: Similar,
    val status: String,
    val vote_average: Double,
    val vote_count: Int
)