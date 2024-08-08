package com.example.challengeconexa.repository

import com.example.challengeconexa.data_source.DataSource
import com.example.challengeconexa.service.model.New
import com.example.challengeconexa.service.model.User
import com.example.challengeconexa.utils.Result
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : Repository {

    override suspend fun getNews(): Result<List<New>> {
        return dataSource.getNews()
    }

    override suspend fun searchNews(query: String): Result<List<New>> {
        return dataSource.searchNew(query)
    }

    override suspend fun getUsers(): Result<List<User>> {
        return dataSource.getUsers()
    }


}