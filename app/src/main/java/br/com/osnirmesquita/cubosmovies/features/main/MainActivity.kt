package br.com.osnirmesquita.cubosmovies.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.osnirmesquita.cubosmovies.R
import br.com.osnirmesquita.cubosmovies.data.Api
import br.com.osnirmesquita.cubosmovies.utils.GridItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val api: Api by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        rvMovies.layoutManager = GridLayoutManager(this, 2)
        rvMovies.addItemDecoration(GridItemDecoration(this, R.dimen.movie_item_offset))
        val adapter = MovieAdapter()
        rvMovies.adapter = adapter

        CompositeDisposable().add(
            api.getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    adapter.setMovies(
                        it.result
                            .map { movie -> MovieUI(movie.id, movie.title, movie.pathImage) })
                }, { Timber.e(it) })
        )
    }
}
