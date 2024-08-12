package com.example.challengeconexa.service.model

import java.io.Serializable

data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val email: String,
    val address: Address
): Serializable

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
): Serializable

data class Geo(
    val lat: String,
    val lng: String
): Serializable

