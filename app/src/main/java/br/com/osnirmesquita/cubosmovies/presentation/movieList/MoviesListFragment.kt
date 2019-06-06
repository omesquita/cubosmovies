package br.com.osnirmesquita.cubosmovies.presentation.movieList


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.osnirmesquita.cubosmovies.R
import br.com.osnirmesquita.cubosmovies.model.Movie
import br.com.osnirmesquita.cubosmovies.presentation.movieDetail.MovieDetailActivity
import br.com.osnirmesquita.cubosmovies.utils.GridItemDecoration
import br.com.osnirmesquita.cubosmovies.utils.extensions.displayToast
import br.com.osnirmesquita.cubosmovies.utils.extensions.gone
import br.com.osnirmesquita.cubosmovies.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.android.ext.android.inject

class MoviesListFragment : Fragment(), MovieListContract.View {
    private val presenter: MovieListContract.Presenter by inject()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.presenter.attachView(this)

        setHasOptionsMenu(true)

        rvMovies.layoutManager = GridLayoutManager(context, 2)
        rvMovies.addItemDecoration(GridItemDecoration(context!!, R.dimen.movie_item_offset))
        rvMovies.addOnScrollListener(endScrollListener)

        this.adapter = MovieAdapter()
        rvMovies.adapter = adapter
        this.adapter.onItemClick = {
            presenter.movieClicked(it)
        }

        this.presenter.start(arguments?.getInt(ARG_GENRE_ID) ?: 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main_activity, menu)

        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val menuItem = menu.findItem(R.id.action_search)
        (menuItem.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            setOnQueryTextListener(searchListener)
        }
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                presenter.closeSearch()
                return true
            }

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {

                return true
            }
        })
    }

    /**
     * Listener when the user click to sarch movie
     * */
    private val searchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            presenter.search(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }

    /**
     * Listen when the recycler view is scrolled
     * */
    private val endScrollListener = object : RecyclerView.OnScrollListener() {
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

    override fun showFailSearch(message: String) {
        context?.displayToast(message)
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_GENRE_ID = "genre_id"

        const val ARG_MOVIE_ID = "movie_id"

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