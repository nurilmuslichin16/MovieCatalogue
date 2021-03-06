package com.example.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class RMovieEntity (
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "movieId")
        var movieId: Int,

        @ColumnInfo(name = "image")
        var image: String,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "years")
        var years: String,

        @ColumnInfo(name = "rating")
        var rating: Double,

        @ColumnInfo(name = "category")
        var category: String,

        @ColumnInfo(name = "overview")
        var overview: String,

        @ColumnInfo(name = "release")
        var release: String,

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean
)