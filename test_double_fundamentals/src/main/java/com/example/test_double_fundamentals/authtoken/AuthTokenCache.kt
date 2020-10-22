package com.example.test_double_fundamentals.authtoken

/**
 * Created by Thomy.
 */
interface AuthTokenCache {

    fun cacheAuthToken(authToken: String)

    fun getAuthToken(): String


}