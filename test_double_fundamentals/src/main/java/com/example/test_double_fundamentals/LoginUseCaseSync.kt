package com.example.test_double_fundamentals

import com.example.test_double_fundamentals.authtoken.AuthTokenCache
import com.example.test_double_fundamentals.eventbus.EventBusPoster
import com.example.test_double_fundamentals.eventbus.LoggedInEvent
import com.example.test_double_fundamentals.networking.LoginHttpEndpointSync
import com.example.test_double_fundamentals.networking.NetworkErrorException

/**
 * Created by Thomy.
 */
class LoginUseCaseSync(
    loginHttpEndpointSync: LoginHttpEndpointSync,
    authTokenCache: AuthTokenCache,
    eventBusPoster: EventBusPoster
) {

    enum class UseCaseResult {

        SUCCESS, FAILURE, NETWORK_ERROR

    }


    var mLoginHttpEndpointSync: LoginHttpEndpointSync = loginHttpEndpointSync
    var mAuthTokenCache: AuthTokenCache = authTokenCache
    var mEventBusPoster: EventBusPoster = eventBusPoster

    fun loginSync(userName: String, password: String): UseCaseResult {

        val endpointEndpointResul: LoginHttpEndpointSync.EndpointResult

        try {
            endpointEndpointResul = mLoginHttpEndpointSync.loginSync(userName, password)
        } catch (e: NetworkErrorException) {
            return UseCaseResult.NETWORK_ERROR

        }



        if (isSuccessfulEndpointResult(endpointEndpointResul)) {

            mAuthTokenCache.cacheAuthToken(endpointEndpointResul.getAuthToken())
            mEventBusPoster.postEvent(LoggedInEvent())
            return UseCaseResult.SUCCESS
        } else {
            return UseCaseResult.FAILURE
        }


    }

    private fun isSuccessfulEndpointResult(endpointResult: LoginHttpEndpointSync.EndpointResult): Boolean {

        return endpointResult.getStatus() == LoginHttpEndpointSync.EndpointResultStatus.SUCCESS

    }

}


















