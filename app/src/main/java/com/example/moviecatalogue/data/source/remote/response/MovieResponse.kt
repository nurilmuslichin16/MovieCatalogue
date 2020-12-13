package com.example.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse (
        var movieId: String,
        var image: String,
        var title: String,
        var years: String,
        var rating: String,
        var category: String,
        var overview: String,
        var release: String
): Parcelable