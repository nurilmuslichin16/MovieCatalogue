package com.example.moviecatalogue.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.home.HomeActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        GlobalScope.launch {
            delay(3000L)
            val toHome = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(toHome)
            finish()
        }
    }
}