package com.m4.mcch.avafintest.remote

import androidx.annotation.StringRes

const val RESPONSE_CODE_SUCCESS = 1
const val RESPONSE_CODE_ERROR = 0

/**
 * Generic class for holding success object with its status.
 */
sealed class Response<T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(@StringRes val message: Int) : Response<T>()
}

class AppException(@StringRes val messageRes: Int) : Exception()