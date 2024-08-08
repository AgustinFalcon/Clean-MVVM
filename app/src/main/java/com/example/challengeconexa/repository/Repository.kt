package com.example.challengeconexa.repository

import com.example.challengeconexa.service.model.New
import com.example.challengeconexa.service.model.User
import com.example.challengeconexa.utils.Result

interface Repository {
    suspend fun getNews(): Result<List<New>>
    suspend fun searchNews(query: String): Result<List<New>>
    suspend fun getUsers(): Result<List<User>>
}