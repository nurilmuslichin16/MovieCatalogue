package com.example.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tventities")
data class RTvEntity (
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