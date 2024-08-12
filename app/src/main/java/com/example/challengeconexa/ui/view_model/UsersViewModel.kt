package com.example.challengeconexa.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeconexa.repository.Repository
import com.example.challengeconexa.service.model.User
import com.example.challengeconexa.utils.Event
import com.example.challengeconexa.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _users = MutableLiveData<Event<List<User>>>()
    val users: LiveData<Event<List<User>>> get() = _users

    val errorUsers = MutableLiveData<Event<String>>()
    val failureUsers = MutableLiveData<Event<String>>()



    fun loadUsers() {
        viewModelScope.launch {
            when(val response = repository.getUsers()) {
                is Result.Success -> _users.value = Event(response.data)
                is Result.Error -> errorUsers.value = Event(response.exception?.message ?: "Hubo un error inesperado")
                is Result.Failure -> failureUsers.value = Event(response.message ?: "Algo ha pasado")
            }
        }
    }


}