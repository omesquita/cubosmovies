package br.com.osnirmesquita.cubosmovies.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.osnirmesquita.cubosmovies.R
import br.com.osnirmesquita.cubosmovies.features.model.Genre
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val genres: List<Genre> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val genrePageAdapter = GenrePageAdapter(genres, supportFragmentManager)
        viewPageMain.adapter = genrePageAdapter
        tabs.setupWithViewPager(viewPageMain)
    }
}