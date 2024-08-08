package com.example.challengeconexa.data_source

import com.example.challengeconexa.service.model.New
import com.example.challengeconexa.service.model.User
import com.example.challengeconexa.utils.Result

interface DataSource {

    suspend fun getNews(): Result<List<New>>
    suspend fun searchNew(query: String): Result<List<New>>
    suspend fun getUsers(): Result<List<User>>


}