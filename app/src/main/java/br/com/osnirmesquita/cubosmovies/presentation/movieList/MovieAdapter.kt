package br.com.osnirmesquita.cubosmovies.presentation.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.osnirmesquita.cubosmovies.BuildConfig
import br.com.osnirmesquita.cubosmovies.R
import br.com.osnirmesquita.cubosmovies.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var movies = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: ((Movie) -> Unit)? = null

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                tvTitleMovie.text = movie.title

                itemView.setOnClickListener {
                    onItemClick?.invoke(movies[adapterPosition])
                }

                Glide.with(itemView.context)
                    .load("${BuildConfig.TMDB_IMG_URL}w342${movie.poster}")
                    .into(ivMovie)
            }
        }
    }
}