package br.com.osnirmesquita.cubosmovies.presentation.movieList


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.osnirmesquita.cubosmovies.R
import br.com.osnirmesquita.cubosmovies.model.Movie
import br.com.osnirmesquita.cubosmovies.presentation.movieDetail.MovieDetailActivity
import br.com.osnirmesquita.cubosmovies.utils.GridItemDecoration
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class MoviesListFragment : Fragment(), MovieListContract.View {
    private val presenter: MovieListContract.Presenter by inject()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.presenter.attachView(this)

        rvMovies.layoutManager = GridLayoutManager(context, 2)
        rvMovies.addItemDecoration(GridItemDecoration(context!!, R.dimen.movie_item_offset))
        rvMovies.addOnScrollListener(endScrollListener())

        this.adapter = MovieAdapter()
        rvMovies.adapter = adapter
        this.adapter.onItemClick = {
            presenter.movieClicked(it)
        }

        this.presenter.start(arguments?.getInt(ARG_GENRE_ID) ?: 0)
    }

    private fun endScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            /**
             * When the list is scrolled to the end. Fetch more results.
             * */
            if (!recyclerView.canScrollVertically(1)) {
                presenter.next()
            }
        }
    }

    override fun showMovieDetail(movie: Movie) {
        val intent = Intent(context, MovieDetailActivity::class.java)
            .apply {
                putExtra(ARG_MOVIE_ID, movie.id)
            }

        startActivity(intent)
    }

    override fun loadMovies(movies: List<Movie>) {
        adapter.movies = movies
    }

    override fun onDestroy() {
        this.presenter.dettachView()
        super.onDestroy()
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_GENRE_ID = "genre_id"

        private const val ARG_MOVIE_ID = "movie_id"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(genreId: Long): MoviesListFragment {
            return MoviesListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_GENRE_ID, genreId.toInt())
                }
            }
        }
    }
}