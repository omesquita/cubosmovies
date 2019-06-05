package br.com.osnirmesquita.cubosmovies.presentation.main

import android.os.Bundle
import br.com.osnirmesquita.cubosmovies.R
import br.com.osnirmesquita.cubosmovies.features.main.GenrePageAdapter
import br.com.osnirmesquita.cubosmovies.model.Genre
import br.com.osnirmesquita.cubosmovies.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : BaseActivity<MainContract.Presenter, MainContract.View>(), MainContract.View {

    private val _presenter: MainContract.Presenter by inject()

    override fun getPresenter(): MainContract.Presenter {
        return _presenter
    }

    override fun destroyPresenter() {
        Timber.d("Destroypresenter")
    }

    override fun setupTabs(genres: List<Genre>) {
        val genrePageAdapter = GenrePageAdapter(genres, supportFragmentManager)
        viewPageMain.adapter = genrePageAdapter
        tabs.setupWithViewPager(viewPageMain)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        _presenter.start()

        Timber.d("oncreate")
    }
}
