package com.example.moviecatalogue.data

data class MovieEntity(
    var movieId: String,
    var image: String,
    var title: String,
    var years: String,
    var rating: String,
    var category: String,
    var overview: String,
    var release: String
)