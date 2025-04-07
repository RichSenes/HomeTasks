// ViewModel e stato per le schermate
package com.richsenes.householdtasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richsenes.householdtasks.data.model.Task
import com.richsenes.householdtasks.data.model.User
import com.richsenes.householdtasks.data.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: FirebaseRepository) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loadUser(userId: String) {
        viewModelScope.launch {
            _user.value = repository.getUser(userId)
        }
    }
}