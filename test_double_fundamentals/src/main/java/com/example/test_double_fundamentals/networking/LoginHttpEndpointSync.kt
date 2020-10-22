package com.example.test_double_fundamentals.networking

import kotlin.properties.Delegates

/**
 * Created by Thomy.
 */
interface LoginHttpEndpointSync {


    @Throws(NetworkErrorException::class)
    fun loginSync(userName: String, password: String): EndpointResult

    enum class EndpointResultStatus {
        SUCCESS, AUTH_ERROR, SERVER_ERROR, GENERAL_ERROR
    }


    class EndpointResult(status: EndpointResultStatus, authToken: String)  {

        var mStatus: EndpointResultStatus
        var mAuthToken: String by Delegates.notNull()

        init {

            mStatus = status
            mAuthToken = authToken

        }

        fun getStatus(): EndpointResultStatus {

            return mStatus

        }

        fun getAuthToken(): String {
            return mAuthToken
        }

    }

}