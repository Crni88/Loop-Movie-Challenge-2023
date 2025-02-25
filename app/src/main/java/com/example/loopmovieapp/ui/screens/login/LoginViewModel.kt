package com.example.loopmovieapp.ui.screens.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.loopmovieapp.com.example.loopmovieapp.data.UserRepository
import com.example.loopmovieapp.com.example.loopmovieapp.domain.User

sealed class RegistrationResult {
    data object Success : RegistrationResult()
    data class Error(val message: String) : RegistrationResult()
}

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun register(name: String, email: String, password: String): RegistrationResult {
        if (name.isEmpty()) {
            return RegistrationResult.Error("Name is required")
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return RegistrationResult.Error("Please enter a valid email address")
        }

        if (password.length < 6) {
            return RegistrationResult.Error("Password must be at least 6 characters")
        }

        val user = User(id = 0, name = name, email = email, password = password)

        return if (userRepository.register(user)) {
            RegistrationResult.Success
        } else {
            RegistrationResult.Error("Registration failed. Please try again.")
        }
    }
}
