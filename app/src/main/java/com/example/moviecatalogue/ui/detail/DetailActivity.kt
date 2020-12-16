package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.hide()
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        iv_back.setOnClickListener {
            finish()
        }

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_DETAIL, 0)
            if (movieId != 0) {
                viewModel.setSelectedMovie(movieId)
                val type = extras.getString(EXTRA_TYPE).toString()
                if (type == "movie") {
                    viewModel.getMovie().observe(this, {movie ->
                        tv_title.text = movie.title
                        tv_category.text = movie.category
                        tv_rating.text = movie.rating.toString()
                        tv_release.text = movie.release
                        tv_overview.text = movie.overview

                        Glide.with(this)
                                .load("https://image.tmdb.org/t/p/w500" + movie.image)
                                .apply(
                                        RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                                                .error(R.drawable.ic_default_glide))
                                .into(iv_image_detail)

                        Glide.with(this)
                                .load("https://image.tmdb.org/t/p/w500" + movie.image)
                                .apply(
                                        RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                                                .error(R.drawable.ic_default_glide))
                                .into(iv_back_image_detail)
                    })
                } else {
                    viewModel.getTv().observe(this, {tv ->
                        tv_title.text = tv.title
                        tv_category.text = tv.category
                        tv_rating.text = tv.rating.toString()
                        tv_release.text = tv.release
                        tv_overview.text = tv.overview

                        Glide.with(this)
                                .load("https://image.tmdb.org/t/p/w500" + tv.image)
                                .apply(
                                        RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                                                .error(R.drawable.ic_default_glide))
                                .into(iv_image_detail)

                        Glide.with(this)
                                .load("https://image.tmdb.org/t/p/w500" + tv.image)
                                .apply(
                                        RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                                                .error(R.drawable.ic_default_glide))
                                .into(iv_back_image_detail)
                    })
                }
            }
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_TYPE = "extra_type"
    }
}