package br.com.osnirmesquita.cubosmovies.presentation.movieDetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.osnirmesquita.cubosmovies.BuildConfig
import br.com.osnirmesquita.cubosmovies.R
import br.com.osnirmesquita.cubosmovies.model.Movie
import br.com.osnirmesquita.cubosmovies.presentation.movieList.MoviesListFragment
import br.com.osnirmesquita.cubosmovies.utils.extensions.gone
import br.com.osnirmesquita.cubosmovies.utils.extensions.visible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_movie_detail.*
import org.koin.android.ext.android.inject


class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private val presenter: MovieDetailContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Enable backbutton

        supportActionBar
        //get movieId passed by parameter
        val movieId = intent?.extras?.getLong(MoviesListFragment.ARG_MOVIE_ID, 0) ?: 0

        //Start presenter and attach the view
        presenter.attachView(this)
        presenter.start(movieId)
    }

    /**
     * When the activity will destroyed, then presenter detached the view.
     * */
    override fun onDestroy() {
        presenter.dettachView()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Update views with data of movie and load poster
     *
     * @param [movie] movie to be loaded
     * */
    override fun setMovie(movie: Movie) {
        tvDescription.text = movie.overview
        toolbar.title = movie.title

        Glide.with(this)
            .load("${BuildConfig.TMDB_IMG_URL}w780${movie.poster}")
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
                ): Boolean {
                    progressBar.gone()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?, model: Any?, target: Target<Drawable>?,
                    dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    progressBar.gone()
                    return false
                }

            })
            .into(imgMoviePoster)
    }

    /**
     * Show group of description
     * */
    override fun showContent() {
        gpDescription.visible()
    }

    /**
     * Hide group of description
     * */
    override fun hideContent() {
        gpDescription.gone()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
