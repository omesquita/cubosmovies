package br.com.osnirmesquita.cubosmovies.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.osnirmesquita.cubosmovies.model.Genre
import br.com.osnirmesquita.cubosmovies.presentation.movieList.MoviesListFragment

class GenrePageAdapter(genres: List<Genre>, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    private val _genres = genres

    override fun getCount(): Int {
        return _genres.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return _genres[position].name
    }

    override fun getItem(position: Int): Fragment {
        return MoviesListFragment.newInstance(_genres[position].id)
    }
}