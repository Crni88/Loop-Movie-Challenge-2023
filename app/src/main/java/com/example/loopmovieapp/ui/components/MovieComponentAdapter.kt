package com.example.loopmovieapp.ui.components

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loopmovieapp.R
import com.example.loopmovieapp.databinding.MovieComponentBinding
import com.example.loopmovieapp.domain.Movie

class MovieComponentAdapter(
    private var movies: List<Movie>,
    private val onMovieClick: (Int) -> Unit,
    private val refreshList: () -> Unit = {}
) :
    RecyclerView.Adapter<MovieComponentAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            MovieComponentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: MovieComponentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title
            binding.movieYear.text = movie.releaseDate.substring(0, 4)

            Glide.with(binding.root)
                .load(movie.posterUrl)
                .into(binding.moviePoster)

            if (movie.isBookmarked) {
                binding.bookmarkIcon.setImageResource(R.drawable.ic_favorite_filled)
            } else {
                binding.bookmarkIcon.setImageResource(R.drawable.ic_favorite_empty)
            }

            setStarRating(movie.rating, binding)

            binding.bookmarkIcon.setOnClickListener {
                movie.isBookmarked = !movie.isBookmarked
                if (movie.isBookmarked) {
                    binding.bookmarkIcon.setImageResource(R.drawable.ic_favorite_filled)
                } else {
                    binding.bookmarkIcon.setImageResource(R.drawable.ic_favorite_empty)
                }
                //onMovieClick(movie.id)
                refreshList()
            }

            binding.movieTitle.setOnClickListener {
                onMovieClick(movie.id)
            }
            binding.movieYear.setOnClickListener {
                onMovieClick(movie.id)
            }
            binding.moviePoster.setOnClickListener {
                onMovieClick(movie.id)
            }
        }

        private fun setStarRating(rating: Double, binding: MovieComponentBinding) {
            val starRatingLayout = binding.starRatingLayout
            val stars = arrayOf(
                starRatingLayout.findViewById(R.id.star1),
                starRatingLayout.findViewById(R.id.star2),
                starRatingLayout.findViewById(R.id.star3),
                starRatingLayout.findViewById(R.id.star4),
                starRatingLayout.findViewById<ImageView>(R.id.star5)
            )

            val fullStars = rating.toInt()

            for (i in stars.indices) {
                if (i < fullStars) {
                    stars[i].setImageResource(R.drawable.ic_star_filled)
                } else {
                    stars[i].setImageResource(R.drawable.ic_star_empty)
                }
            }
        }
    }
}