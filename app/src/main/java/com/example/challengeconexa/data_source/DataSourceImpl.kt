package com.example.challengeconexa.data_source

import com.example.challengeconexa.di.RetrofitModule
import com.example.challengeconexa.service.ApiService
import com.example.challengeconexa.service.model.New
import com.example.challengeconexa.service.model.User
import com.example.challengeconexa.utils.Result
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class DataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : DataSource {

    override suspend fun getNews(): Result<List<New>> =
        try {
            val response = apiService.getNews()
            if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                Result.Success(response.body()!!)

            } else {
                Result.Failure(response.code(), response.message())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun searchNew(query: String): Result<List<New>> =
        try {
            val response = apiService.searchNews(query = query)
            if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                Result.Success(response.body()!!)

            } else {
                Result.Failure(response.code(), response.message())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getUsers(): Result<List<User>> =
        try {
            val response = apiService.getUsers()
            if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                Result.Success(response.body()!!)

            } else {
                Result.Failure(response.code(), response.message())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
}
