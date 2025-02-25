package com.example.loopmovieapp.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loopmovieapp.R
import com.example.loopmovieapp.databinding.FragmentHomeBinding
import com.example.loopmovieapp.ui.components.MovieComponentAdapter
import com.example.loopmovieapp.ui.components.MovieHorizontalAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var movieAdapter: MovieComponentAdapter
    private lateinit var horizontalAdapter: MovieHorizontalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchIcon.setOnClickListener { onSearchClicked() }

        movieAdapter = MovieComponentAdapter(
            emptyList(),
            onMovieClick = { movieId ->
                onMovieClicked(movieId)
            },
            refreshList = {
                homeViewModel.loadBookmarkedMovies()
            }
        )

        binding.recyclerViewMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }

        horizontalAdapter = MovieHorizontalAdapter(emptyList()) { movie ->
            onMovieClicked(movie.id)
        }
        binding.recyclerViewHorizontal.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = horizontalAdapter
        }

        homeViewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieAdapter.updateMovies(movies)
        }

        homeViewModel.bookmarkedMovies.observe(viewLifecycleOwner) { movies ->
            horizontalAdapter.updateMovies(movies.filter { it.isBookmarked })
        }
        homeViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.nameText.text = user.name
            }
        }

        homeViewModel.loadMovies()
        homeViewModel.loadBookmarkedMovies()
    }

    private fun onMovieClicked(movieId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movieId)
        findNavController().navigate(action)
    }

    private fun onSearchClicked() {
        val action = HomeFragmentDirections.actionHomeFragmentToAllMoviesFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}