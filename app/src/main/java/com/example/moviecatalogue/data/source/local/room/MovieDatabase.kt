package com.example.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.data.source.local.entity.RTvEntity

@Database(
    entities = [RMovieEntity::class, RTvEntity::class],
    version = 1,
    exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun MovieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    MovieDatabase::class.java,
                    "Movies.db").build()
            }
    }

}