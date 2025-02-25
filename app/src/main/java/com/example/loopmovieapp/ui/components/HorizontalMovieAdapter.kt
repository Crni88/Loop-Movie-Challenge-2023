package com.example.loopmovieapp.ui.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loopmovieapp.R
import com.example.loopmovieapp.domain.Movie

class MovieHorizontalAdapter(
    private var movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieHorizontalAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_horizontal, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imagePoster: ImageView = itemView.findViewById(R.id.imagePoster)

        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load(movie.posterUrl)
                .into(imagePoster)

            itemView.setOnClickListener {
                onItemClick(movie)
            }
        }
    }
}
