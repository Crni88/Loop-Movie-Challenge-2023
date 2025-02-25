package com.example.loopmovieapp.ui.screens.allMovies

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loopmovieapp.databinding.AllMoviesFragmentBinding
import com.example.loopmovieapp.ui.components.MovieComponentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllMoviesFragment : Fragment() {

    private val allMoviesViewModel: AllMoviesViewModel by viewModel()

    private var _binding: AllMoviesFragmentBinding? = null
    private val binding get() = _binding!!

    private val movieAdapter by lazy {
        MovieComponentAdapter(
            emptyList(),
            onMovieClick = { movieId ->
                navigateToMovieDetail(movieId)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = AllMoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.movieRecyclerView.adapter = movieAdapter

        allMoviesViewModel.filteredMovies.observe(viewLifecycleOwner) { filteredMovies ->
            movieAdapter.updateMovies(filteredMovies)
        }

        binding.backButton.setOnClickListener {
            navigateBack()
        }

        allMoviesViewModel.loadMovies()

        setupSearchBar()
    }

    private fun setupSearchBar() {
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(editable: Editable?) {
                val query = editable.toString().trim()
                allMoviesViewModel.filterMovies(query)
            }
        })
    }

    private fun navigateBack() {
        val navController = findNavController()
        navController.popBackStack()
    }

    private fun navigateToMovieDetail(movieId: Int) {
        val action =
            AllMoviesFragmentDirections.actionAllMoviesFragmentToMovieDetailFragment(movieId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
