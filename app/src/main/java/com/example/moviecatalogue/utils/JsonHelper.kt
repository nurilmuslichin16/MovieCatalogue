package com.example.moviecatalogue.utils

import android.content.Context
import com.example.moviecatalogue.data.MovieEntity
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieEntity> {
        val list = ArrayList<MovieEntity>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("movie_id")
                val image = movie.getString("image")
                val title = movie.getString("title")
                val years = movie.getString("years")
                val rating = movie.getString("rating")
                val category = movie.getString("category")
                val overview = movie.getString("overview")
                val release = movie.getString("release")

                val movieResponse = MovieEntity(id, image, title, years, rating, category, overview, release)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTv(): List<MovieEntity> {
        val list = ArrayList<MovieEntity>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvResponses.json").toString())
            val listArray = responseObject.getJSONArray("tv")
            for (i in 0 until listArray.length()) {
                val tv = listArray.getJSONObject(i)

                val id = tv.getString("movie_id")
                val image = tv.getString("image")
                val title = tv.getString("title")
                val years = tv.getString("years")
                val rating = tv.getString("rating")
                val category = tv.getString("category")
                val overview = tv.getString("overview")
                val release = tv.getString("release")

                val tvResponse = MovieEntity(id, image, title, years, rating, category, overview, release)
                list.add(tvResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

}