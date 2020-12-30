package com.example.moviecatalogue.ui.menu.movies

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_movies.view.*

class FavMovieAdapter: PagedListAdapter<RMovieEntity, FavMovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    private var listMovies = ArrayList<RMovieEntity>()

    fun setMovies(movies: List<RMovieEntity>?) {
        if (movies.isNullOrEmpty()) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieAdapter.MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavMovieAdapter.MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(movie: RMovieEntity) {
            var overview = ""
            overview = when {
                movie.overview == "" -> {
                    "-"
                }
                movie.overview.length < 50 -> {
                    movie.overview.substring(0,30) + "..."
                }
                movie.overview.length < 100 -> {
                    movie.overview.substring(0,50) + "..."
                }
                else -> {
                    movie.overview.substring(0,100) + "..."
                }
            }
            with(itemView) {
                tv_item_title.text = movie.title
                tv_item_years.text = movie.years
                tv_item_rating.text = movie.rating.toString()
                tv_item_overview.text = overview
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

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RMovieEntity>() {
            override fun areItemsTheSame(oldItem: RMovieEntity, newItem: RMovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: RMovieEntity, newItem: RMovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}