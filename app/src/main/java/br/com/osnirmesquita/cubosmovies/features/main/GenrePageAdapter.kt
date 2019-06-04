package br.com.osnirmesquita.cubosmovies.features.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.osnirmesquita.cubosmovies.features.model.Genre

class GenrePageAdapter(genres: List<Genre>, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    private val _genres = genres

    override fun getCount(): Int {
        return _genres.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return _genres.get(position).name
    }

    override fun getItem(position: Int): Fragment {
        return MoviesListFragment.newInstance(position + 1)
    }
}