package com.example.loopmovieapp.ui.screens.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loopmovieapp.R
import com.example.loopmovieapp.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.signUpButton.setOnClickListener {
            val name = binding.nameEditText.text?.toString()?.trim() ?: ""
            val email = binding.emailEditText.text?.toString()?.trim() ?: ""
            val password = binding.passwordEditText.text?.toString() ?: ""
            val confirmPassword = binding.confirmPasswordEditText.text?.toString() ?: ""

            if (password != confirmPassword) {
                binding.confirmPasswordInputLayout.error = "Passwords do not match"
                return@setOnClickListener
            } else {
                binding.confirmPasswordInputLayout.error = null
            }

            when (val result = loginViewModel.register(name, email, password)) {
                is RegistrationResult.Success -> {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }

                is RegistrationResult.Error -> {
                    Snackbar.make(binding.root, result.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
