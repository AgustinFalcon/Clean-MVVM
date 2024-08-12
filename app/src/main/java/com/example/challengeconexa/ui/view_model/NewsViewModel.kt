package com.example.challengeconexa.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeconexa.repository.Repository
import com.example.challengeconexa.service.model.New
import com.example.challengeconexa.service.model.User
import com.example.challengeconexa.utils.Event
import com.example.challengeconexa.utils.HttpCode
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


     val failureNews = MutableLiveData<Event<StateErrorNew>>()
     val errorNews = MutableLiveData<Event<String>>()

    fun loadNews() {
        viewModelScope.launch {
            when(val response = repository.getNews()) {
                is Result.Success -> _allNews.value = Event(response.data)
                is Result.Error -> errorNews.value = Event(response.exception?.message ?: "Hubo un error inesperado")
                is Result.Failure -> {
                    when (response.code) {
                        HttpCode.ErrorInvalidUser.code -> failureNews.value = Event(StateErrorNew.ErrorInvalidUser(response.message ?: "Usuario invalido"))
                        HttpCode.ErrorNotInternet.code -> failureNews.value = Event(StateErrorNew.ErrorNotInternet(response.message ?: "No tiene internet"))
                        HttpCode.ErrorUnknownUser.code -> failureNews.value = Event(StateErrorNew.ErrorUnknownUser(response.message ?: "Usuario desconocido"))
                        else -> {
                            failureNews.value = Event(StateErrorNew.Default(response.message ?: "Algo ha ocurrido"))
                        }
                    }
                }
            }

        }
    }

    fun searchNews(query: String) {
        viewModelScope.launch {
            when(val response = repository.searchNews(query)) {
                is Result.Success -> {
                    // Se realiza filtrado manual ya que el api no soporta el mismo
                    val filteredNews = response.data.filter {
                        it.title.contains(query, ignoreCase = true)
                    }
                    _allNews.value = Event(filteredNews)
                }
                is Result.Error -> errorNews.value = Event(response.exception?.message ?: "Hubo un error inesperado")
                is Result.Failure -> failureNews.value = Event(StateErrorNew.Default(response.message ?: "Algo ha ocurrido"))
            }
        }
    }
}

sealed class StateErrorNew() {
    data class ErrorNotInternet(val message: String): StateErrorNew()
    data class ErrorInvalidUser(val message: String): StateErrorNew()
    data class ErrorUnknownUser(val message: String): StateErrorNew()
    data class Default(val message: String): StateErrorNew()
}