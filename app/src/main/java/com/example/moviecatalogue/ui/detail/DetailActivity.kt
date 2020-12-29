package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.hide()
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        iv_back.setOnClickListener {
            finish()
        }

        val extras = intent.extras
        val type = extras?.getString(EXTRA_TYPE)
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_DETAIL, 0)
            if (movieId != 0) {
                viewModel.setSelectedMovie(movieId)
                if (type == "movie") {
                    viewModel.movieDetail.observe(this, {movie ->
                        if (movie != null) {
                            when (movie.status) {
                                Status.LOADING -> progress_bar_detail.visibility = View.VISIBLE
                                Status.SUCCESS -> {
                                    progress_bar_detail.visibility = View.GONE

                                    tv_title.text = movie.data?.title
                                    tv_category.text = movie.data?.category
                                    tv_rating.text = movie.data?.rating.toString()
                                    tv_release.text = movie.data?.release
                                    tv_overview.text = movie.data?.overview

                                    val isFavorite = movie.data?.isFavorite
                                    setFavorite(isFavorite)

                                    Glide.with(this)
                                            .load("https://image.tmdb.org/t/p/w500" + movie.data?.image)
                                            .apply(
                                                    RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                                                            .error(R.drawable.ic_default_glide))
                                            .into(iv_image_detail)

                                    Glide.with(this)
                                            .load("https://image.tmdb.org/t/p/w500" + movie.data?.image)
                                            .apply(
                                                    RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                                                            .error(R.drawable.ic_default_glide))
                                            .into(iv_back_image_detail)
                                }
                                Status.ERROR -> {
                                    progress_bar_detail.visibility = View.GONE
                                    Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                } else {
                    viewModel.tvDetail.observe(this, {tv ->
                        if (tv != null) {
                            when (tv.status) {
                                Status.LOADING -> progress_bar_detail.visibility = View.VISIBLE
                                Status.SUCCESS -> {
                                    progress_bar_detail.visibility = View.GONE
                                    tv_title.text = tv.data?.title
                                    tv_category.text = tv.data?.category
                                    tv_rating.text = tv.data?.rating.toString()
                                    tv_release.text = tv.data?.release
                                    tv_overview.text = tv.data?.overview

                                    val isFavorite = tv.data?.isFavorite
                                    setFavorite(isFavorite)

                                    Glide.with(this)
                                            .load("https://image.tmdb.org/t/p/w500" + tv.data?.image)
                                            .apply(
                                                    RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                                                            .error(R.drawable.ic_default_glide))
                                            .into(iv_image_detail)

                                    Glide.with(this)
                                            .load("https://image.tmdb.org/t/p/w500" + tv.data?.image)
                                            .apply(
                                                    RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                                                            .error(R.drawable.ic_default_glide))
                                            .into(iv_back_image_detail)
                                }
                                Status.ERROR -> {
                                    progress_bar_detail.visibility = View.GONE
                                    Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                }
            }
        }

        fab_add_favorite.setOnClickListener {
            if (type == "movie") {
                viewModel.setFavoriteMovie()
            } else {
                viewModel.setFavoriteTv()
            }
        }
    }

    private fun setFavorite(isFavorite: Boolean?) {
        if (isFavorite == null) return
        if (isFavorite) {
            fab_add_favorite.setImageResource(R.drawable.ic_favorite_enable)
        } else {
            fab_add_favorite.setImageResource(R.drawable.ic_favorite_disable)
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_TYPE = "extra_type"
    }
}