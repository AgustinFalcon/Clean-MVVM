package com.example.challengeconexa.utils

import java.lang.Exception
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val code: Int, val message: String?): Result<Nothing>()
    data class Error(val exception: java.lang.Exception?) : Result<Nothing>()


    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Failure -> "Failure[error=$message]"
        }
    }
}


/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null
