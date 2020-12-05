package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MovieEntity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.hide()
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        iv_back.setOnClickListener {
            finish()
        }

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_DETAIL)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                val type = extras.getString(EXTRA_TYPE).toString()
                if (type == "movie") {
                    populateMovie(viewModel.getMovie())
                } else {
                    populateMovie(viewModel.getTv())
                }
            }
        }
    }

    private fun populateMovie(movieEntity: MovieEntity) {
        tv_title.text = movieEntity.title
        tv_category.text = movieEntity.category
        tv_rating.text = movieEntity.rating
        tv_release.text = movieEntity.release
        tv_overview.text = movieEntity.overview

        Glide.with(this)
            .load(movieEntity.image)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                    .error(R.drawable.ic_default_glide))
            .into(iv_image_detail)

        Glide.with(this)
            .load(movieEntity.image)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                    .error(R.drawable.ic_default_glide))
            .into(iv_back_image_detail)
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_TYPE = "extra_type"
    }
}