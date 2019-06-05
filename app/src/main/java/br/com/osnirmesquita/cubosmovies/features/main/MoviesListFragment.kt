package br.com.osnirmesquita.cubosmovies.features.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.osnirmesquita.cubosmovies.R
import br.com.osnirmesquita.cubosmovies.utils.GridItemDecoration
import kotlinx.android.synthetic.main.fragment_movie_list.*
import timber.log.Timber

class MoviesListFragment : Fragment() {

    private var count = 1
    private lateinit var adapter: MovieAdapter
    private var genreId: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genreId = arguments?.getLong(ARG_GENRE_ID) ?: 0

        adapter = MovieAdapter()
        rvMovies.layoutManager = GridLayoutManager(context, 2)
        rvMovies.addItemDecoration(GridItemDecoration(context!!, R.dimen.movie_item_offset))
        rvMovies.adapter = adapter

        rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Timber.d("onScrollStateChanged($newState = ${recyclerView.canScrollVertically(1)})")

                if (!recyclerView.canScrollVertically(1)) {
                    count++
                }
            }
        })
    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_GENRE_ID = "genre_id"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(genreId: Long): MoviesListFragment {
            return MoviesListFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_GENRE_ID, genreId)
                }
            }
        }
    }
}