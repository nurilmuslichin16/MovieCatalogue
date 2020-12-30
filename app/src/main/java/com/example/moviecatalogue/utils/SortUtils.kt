package com.example.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"

    fun getSortedQuery(filter: String, type: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM ")

        if (type == "movie") {
            simpleQuery.append("movieentities WHERE isFavorite = 1 ")
        } else {
            simpleQuery.append("tventities WHERE isFavorite = 1 ")
        }

        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY movieId ASC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY movieId DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}