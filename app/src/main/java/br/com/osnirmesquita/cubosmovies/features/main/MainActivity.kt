package br.com.osnirmesquita.cubosmovies.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.osnirmesquita.cubosmovies.R
import br.com.osnirmesquita.cubosmovies.data.repository.GenreRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val genreRepository: GenreRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        CompositeDisposable().add(
            genreRepository.getGenreByIds(28, 14, 18, 878)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ genres ->
                    val genrePageAdapter = GenrePageAdapter(genres, supportFragmentManager)
                    viewPageMain.adapter = genrePageAdapter
                    tabs.setupWithViewPager(viewPageMain)
                }, { Timber.e(it) })
        )
    }
}