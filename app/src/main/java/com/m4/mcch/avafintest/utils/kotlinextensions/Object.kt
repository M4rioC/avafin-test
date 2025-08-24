package com.m4.mcch.avafintest.utils.kotlinextensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 *  Converts an object to a JSON string.
 */
inline fun <reified T> T.toJson(): String {
    return Gson().toJson(this)
}

/**
 * Converts a JSON string to an object of type T.
 */
inline fun <reified T> String.fromJson(): T {
    return Gson().fromJson(this, object : TypeToken<T>() {}.type)
}