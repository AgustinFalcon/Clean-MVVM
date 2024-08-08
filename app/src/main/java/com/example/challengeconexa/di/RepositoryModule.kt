package com.example.challengeconexa.di

import android.content.Context
import com.example.challengeconexa.data_source.DataSourceImpl
import com.example.challengeconexa.repository.Repository
import com.example.challengeconexa.repository.RepositoryImpl
import com.example.challengeconexa.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Volatile
    var repository: Repository? = null


    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        synchronized(this) {
            return repository ?: repository ?: createRepository(apiService)
        }
    }


    fun createRepository(apiService: ApiService): Repository {
        val newRepo = RepositoryImpl(DataSourceImpl(apiService))

        repository = newRepo
        return newRepo
    }
}
