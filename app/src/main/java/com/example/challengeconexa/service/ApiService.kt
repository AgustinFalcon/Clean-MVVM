package com.example.challengeconexa.service

import com.example.challengeconexa.service.model.New
import com.example.challengeconexa.service.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    // Endpoint para obtener las noticias
    @GET("/posts")
    suspend fun getNews(): Response<List<New>>

    // Endpoint para buscar noticias por título o contenido
    @GET("/posts")
    suspend fun searchNews(@Query("q") query: String): Response<List<New>>

    // Endpoint para obtener la lista de usuarios
    @GET("/users")
    suspend fun getUsers(): Response<List<User>>
}
