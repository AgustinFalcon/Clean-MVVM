package com.example.challengeconexa.service.model

import java.io.Serializable

data class New(
    val userId: Int,
    val id: Int,
    val image: String,
    val title: String,
    val status: String,
    val category: String
): Serializable