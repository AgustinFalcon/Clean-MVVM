package com.example.challengeconexa.utils



enum class HttpCode(val code: Int) {
    ErrorNotInternet(404),
    ErrorInvalidUser(401),
    ErrorUnknownUser(402)
}
