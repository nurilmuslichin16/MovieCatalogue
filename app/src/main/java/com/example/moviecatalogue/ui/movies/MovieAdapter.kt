package com.example.moviecatalogue.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_movies.view.*

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<MovieResponse>()

    fun setMovies(movies: List<MovieResponse>?) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieResponse) {
            with(itemView) {
                tv_item_title.text = movie.title
                tv_item_years.text = movie.years
                tv_item_rating.text = movie.rating.toString()
                tv_item_category.text = movie.category.toString()
                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_DETAIL, movie.movieId)
                        putExtra(DetailActivity.EXTRA_TYPE, "movie")
                    }
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                            .error(R.drawable.ic_default_glide))
                    .into(img_poster)
            }
        }
    }

}