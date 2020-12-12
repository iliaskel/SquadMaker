package com.example.squadmaker.model.remotedatasource.retrofit

data class Similar(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)