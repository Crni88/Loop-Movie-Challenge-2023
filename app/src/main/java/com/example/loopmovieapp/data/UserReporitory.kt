package com.example.loopmovieapp.com.example.loopmovieapp.data

import com.example.loopmovieapp.com.example.loopmovieapp.domain.User

class UserRepository {
    private val userList = mutableListOf<User>()
    fun register(user: User): Boolean {
        userList.add(user)
        return true
    }

    fun getUser(): User? {
        return userList.firstOrNull()
    }
}
