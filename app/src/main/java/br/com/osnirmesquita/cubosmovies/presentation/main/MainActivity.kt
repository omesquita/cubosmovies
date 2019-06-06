package br.com.osnirmesquita.cubosmovies.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.osnirmesquita.cubosmovies.R
import br.com.osnirmesquita.cubosmovies.model.Genre
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter: MainContract.Presenter by inject()

    override fun setUpTabs(genres: List<Genre>) {
        val genrePageAdapter =
            GenrePageAdapter(genres, supportFragmentManager)
        viewPageMain.adapter = genrePageAdapter
        tabs.setupWithViewPager(viewPageMain)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        presenter.attachView(this)

        presenter.start()
    }

    override fun onDestroy() {
        presenter.dettachView()
        super.onDestroy()
    }
}
