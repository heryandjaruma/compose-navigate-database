package com.example.cobadatabase

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val db = DatabaseProvider.getDatabase(application)

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users //exposed as live data

    init {
        observeUsers()
    }

    fun insertUser(user: User){
        viewModelScope.launch {
            db.userDao().insertAll(user)
        }
    }

    fun observeUsers(){
        viewModelScope.launch {
            db.userDao().getAll().collect(){
                _users.value = it
            }
        }
    }
}