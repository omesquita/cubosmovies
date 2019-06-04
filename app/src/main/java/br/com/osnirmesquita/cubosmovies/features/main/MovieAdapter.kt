package br.com.osnirmesquita.cubosmovies.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.osnirmesquita.cubosmovies.BuildConfig
import br.com.osnirmesquita.cubosmovies.R
import br.com.osnirmesquita.cubosmovies.features.model.MovieUI
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies = listOf<MovieUI>()

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setMovies(moviesList: List<MovieUI>) {
        movies = moviesList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieUI: MovieUI) {
            with(itemView) {
                tvTitleMovie.text = movieUI.title

                itemView.pbLoadMovie.indeterminateDrawable

                Glide.with(itemView.context)
                    .load("${BuildConfig.TMDB_IMG_URL}w342${movieUI.image}")
                    .into(ivMovie)
            }
        }
    }
}