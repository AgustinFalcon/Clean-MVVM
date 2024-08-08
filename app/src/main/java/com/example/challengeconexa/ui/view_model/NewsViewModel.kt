package com.example.challengeconexa.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeconexa.repository.Repository
import com.example.challengeconexa.service.model.New
import com.example.challengeconexa.service.model.User
import com.example.challengeconexa.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.challengeconexa.utils.Result

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _allNews = MutableLiveData<Event<List<New>>>()
    val allNews: LiveData<Event<List<New>>> get() = _allNews

    private val _users = MutableLiveData<Event<Result<List<User>>>>()
    val users: LiveData<Event<Result<List<User>>>> get() = _users


    private val _errorNews = MutableLiveData<Event<String>>()
    private val _failureNews = MutableLiveData<Event<String>>()

    fun loadNews() {
        viewModelScope.launch {
            when(val response = repository.getNews()) {
                is Result.Success -> _allNews.value = Event(response.data)
                is Result.Error -> _errorNews.value = Event(response.exception?.message ?: "Hubo un error inesperado")
                is Result.Failure -> _failureNews.value = Event(response.message ?: "Algo ha pasado")
            }

        }
    }

    fun searchNews(query: String) {
        viewModelScope.launch {
            when(val response = repository.searchNews(query)) {
                is Result.Success -> _allNews.value = Event(response.data)
                is Result.Error -> _errorNews.value = Event(response.exception?.message ?: "Hubo un error inesperado")
                is Result.Failure -> _failureNews.value = Event(response.message ?: "Algo ha pasado")
            }
        }
    }

    fun loadUsers() {
        viewModelScope.launch {
            _users.value = Event(repository.getUsers())
        }
    }
}