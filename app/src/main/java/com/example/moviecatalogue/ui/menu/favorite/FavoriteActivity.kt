package com.example.moviecatalogue.ui.menu.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val favSectionPageAdapter = FavSectionsPagerAdapter(this, supportFragmentManager)
        view_pager_favorite.adapter = favSectionPageAdapter
        tabs_favorite.setupWithViewPager(view_pager_favorite)

        supportActionBar?.title = "List Favorite"
        supportActionBar?.elevation = 0f
    }
}