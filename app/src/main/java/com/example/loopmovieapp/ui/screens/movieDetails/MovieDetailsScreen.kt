package com.example.loopmovieapp.ui.screens.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.loopmovieapp.R
import com.example.loopmovieapp.com.example.loopmovieapp.ui.components.MoviePoster
import com.example.loopmovieapp.com.example.loopmovieapp.ui.screens.movieDetails.MovieDetailsViewModel
import com.example.loopmovieapp.domain.Movie
import com.example.loopmovieapp.ui.components.KeyFactsComponent
import com.example.loopmovieapp.ui.components.MovieBody
import com.example.loopmovieapp.ui.components.MovieBodyTitle
import com.example.loopmovieapp.ui.components.MovieSubtitle
import com.example.loopmovieapp.ui.components.MovieTitle
import com.example.loopmovieapp.ui.components.MovieTitleSecondary
import com.example.loopmovieapp.ui.components.RatingBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: MovieDetailsViewModel by viewModel()

        return ComposeView(requireContext()).apply {
            setContent {
                val movieId = arguments?.getInt("movieId") ?: 0
                val movie = viewModel.findMovieById(movieId)
                val navController = findNavController()
                MovieDetailScreen(
                    movie = movie,
                    onBackPressed = { navController.popBackStack() },
                    setBookmark = viewModel::setMovieBookmark
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun MovieDetailScreen(
    onBackPressed: () -> Boolean,
    movie: Movie?,
    setBookmark: (Int, Boolean) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(74.dp)
                    .background(color = Color(0xFFFFFFFF).copy(alpha = 0.3f))
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var isBookmarked by remember { mutableStateOf(movie?.isBookmarked ?: false) }
                Icon(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        isBookmarked = !isBookmarked
                        setBookmark(movie?.id ?: 0, isBookmarked)
                    },
                    painter = if (isBookmarked) {
                        painterResource(id = R.drawable.ic_favorite_filled)
                    } else {
                        painterResource(id = R.drawable.ic_favorite_empty)
                    },
                    contentDescription = "Favorite",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(30.dp))
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(
                            color = Color(0xFFF2F2F7),
                            shape = CircleShape
                        )
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.clickable { onBackPressed() },
                        painter = painterResource(id = R.drawable.icon_xmark),
                        contentDescription = "Favorite",
                        tint = Color.Black
                    )
                }

            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            MoviePoster(moviePosterUrl = movie?.posterUrl ?: "")
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            RatingBar(rating = movie?.rating ?: 0.0)
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .padding(horizontal = 96.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                MovieSubtitle(value = movie?.releaseDate ?: "")
                MovieSubtitle(value = " · ")
                MovieSubtitle(value = movie?.formattedRuntime ?: "")
            }
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            FlowRow(
                modifier = Modifier
                    .padding(horizontal = 96.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                MovieTitle(movie?.title ?: "")
                MovieTitleSecondary((" (" + movie?.releaseDate?.substring(0, 4) + ")"))
            }

        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            FlowRow(
                modifier = Modifier
                    .padding(horizontal = 53.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                movie?.genres?.forEach { genre ->
                    Text(
                        genre,
                        modifier = Modifier
                            .clip(RoundedCornerShape(11.dp))
                            .background(color = Color(0xFF141C25).copy(alpha = 0.05f))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(45.dp))
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                MovieBodyTitle("Overview")
                Spacer(modifier = Modifier.height(8.dp))
                MovieBody(movie?.overview ?: "")
                Spacer(modifier = Modifier.height(8.dp))
                MovieBodyTitle("Key facts")
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    KeyFactsComponent(
                        title = R.string.budget,
                        value = movie?.formattedBudget ?: "",
                        isMoneyValue = true,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    KeyFactsComponent(
                        isMoneyValue = true,
                        value = movie?.formatedRevenue ?: "",
                        title = R.string.revenue,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    KeyFactsComponent(
                        title = R.string.original_language,
                        value = movie?.language ?: "",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    KeyFactsComponent(
                        title = R.string.rating,
                        value = (movie?.rating
                            ?: 0.0).toString() + " (" + movie?.reviews.toString() + ")",
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(70.dp))
            }
        }
    }
}


