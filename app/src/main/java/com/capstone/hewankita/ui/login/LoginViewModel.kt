package com.capstone.hewankita.ui.login

import androidx.lifecycle.*
import com.capstone.hewankita.data.AnimalRepository

class LoginViewModel(private val animalRepository: AnimalRepository) : ViewModel() {
    fun login(email: String, password: String) = animalRepository.login(email, password)
}
