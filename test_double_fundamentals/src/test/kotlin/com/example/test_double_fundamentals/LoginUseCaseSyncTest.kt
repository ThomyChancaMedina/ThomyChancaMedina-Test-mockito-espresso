package com.example.test_double_fundamentals

import com.example.test_double_fundamentals.authtoken.AuthTokenCache
import com.example.test_double_fundamentals.eventbus.EventBusPoster
import com.example.test_double_fundamentals.eventbus.LoggedInEvent
import com.example.test_double_fundamentals.networking.LoginHttpEndpointSync
import com.example.test_double_fundamentals.networking.NetworkErrorException
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.properties.Delegates

/**
 * Created by Thomy.
 */
class LoginUseCaseSyncTest {

    companion object {
        var USERNAME = "username"
        var PASSWORD = "password"
        var AUTH_TOKEN = "authToken"
    }

      var mLoginHttpEndpointSyncTd = LoginHttpEndpointSyncTd()

      var mEventBusPosterTd= EventBusPosterTd()

      var mAuthTokenCacheTd= AuthTokenCacheTd()


    lateinit var SUT: LoginUseCaseSync

    @Before
    fun setUp() {

        mLoginHttpEndpointSyncTd = LoginHttpEndpointSyncTd()
        mAuthTokenCacheTd = AuthTokenCacheTd()
        mEventBusPosterTd = EventBusPosterTd()

        SUT = LoginUseCaseSync(mLoginHttpEndpointSyncTd, mAuthTokenCacheTd, mEventBusPosterTd)

    }


    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_success_usernameAndPasswordToEndpoind() {
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mLoginHttpEndpointSyncTd.mUsername, `is`(USERNAME))
        assertThat(mLoginHttpEndpointSyncTd.mPassword, `is`(PASSWORD))

    }


    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_success_authTokenCached() {
        SUT.loginSync(USERNAME, PASSWORD)

        assertThat(mAuthTokenCacheTd.getAuthToken(), `is`(AUTH_TOKEN))


    }


    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_generalError_authTokenNotCached() {
        mLoginHttpEndpointSyncTd.mInGeneralError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mAuthTokenCacheTd.getAuthToken(), `is`(""))

    }

    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_authError_authTokenNotCached() {
        mLoginHttpEndpointSyncTd.mInGeneralError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mAuthTokenCacheTd.getAuthToken(), `is`(""))

    }

    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_serverError_authTokenNotCached() {
        mLoginHttpEndpointSyncTd.mIsServerError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mAuthTokenCacheTd.getAuthToken(), `is`(""))

    }


    @Test
    @Throws(java.lang.Exception::class)
    fun loginSyc_success_loggedInEventPosted() {
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mEventBusPosterTd.mEvent, `is`(instanceOf(LoggedInEvent::class.java)))
    }


    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_generalError_noInteractionWithEventBusPoster() {
        mLoginHttpEndpointSyncTd.mInGeneralError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mEventBusPosterTd.mInteractionsCount, `is`(0))

    }

    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_authError_noInteractionWithEventBusPoster() {
        mLoginHttpEndpointSyncTd.mIsAuthError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mEventBusPosterTd.mInteractionsCount, `is`(0))

    }

    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_serverError_noInteractionWithEventBusPoster() {
        mLoginHttpEndpointSyncTd.mIsServerError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mEventBusPosterTd.mInteractionsCount, `is`(0))

    }



    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_success_successReturned() {
        val result: LoginUseCaseSync.UseCaseResult = SUT.loginSync(USERNAME, PASSWORD)
        assertThat(result, `is`(LoginUseCaseSync.UseCaseResult.SUCCESS))

    }


    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_serverError_failureReturned() {

        mLoginHttpEndpointSyncTd.mIsServerError = true
        val result: LoginUseCaseSync.UseCaseResult = SUT.loginSync(USERNAME, PASSWORD)
        assertThat(result, `is`(LoginUseCaseSync.UseCaseResult.FAILURE))

    }

    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_authorError_failureReturned() {

        mLoginHttpEndpointSyncTd.mIsAuthError = true
        val result: LoginUseCaseSync.UseCaseResult = SUT.loginSync(USERNAME, PASSWORD)
        assertThat(result, `is`(LoginUseCaseSync.UseCaseResult.FAILURE))

    }

    @Test
    @Throws(java.lang.Exception::class)
    fun loginSync_generalError_failureReturned() {

        mLoginHttpEndpointSyncTd.mInGeneralError = true
        val result: LoginUseCaseSync.UseCaseResult = SUT.loginSync(USERNAME, PASSWORD)
        assertThat(result, `is`(LoginUseCaseSync.UseCaseResult.FAILURE))

    }


    //network -network  error returned

    @Test
    fun loginSync_networkError_networkErrorReturned() {
        mLoginHttpEndpointSyncTd.mIsNetworkError = true
        val result: LoginUseCaseSync.UseCaseResult = SUT.loginSync(USERNAME, PASSWORD)
        assertThat(result, `is`(LoginUseCaseSync.UseCaseResult.NETWORK_ERROR))

    }

    //-------------------------------------------------------------------------
    //Helper class


    class LoginHttpEndpointSyncTd : LoginHttpEndpointSync {

        lateinit var mUsername: String
        lateinit var mPassword: String
        var mInGeneralError: Boolean = false
        var mIsAuthError: Boolean = false
        var mIsServerError: Boolean = false
        var mIsNetworkError: Boolean = false


        @Throws(NetworkErrorException::class)
        override fun loginSync(
            userName: String,
            password: String
        ): LoginHttpEndpointSync.EndpointResult {


            mUsername = userName
            mPassword = password

            if (mInGeneralError) {

                return LoginHttpEndpointSync.EndpointResult(
                    LoginHttpEndpointSync.EndpointResultStatus.GENERAL_ERROR,
                    ""
                )
            } else if (mIsAuthError) {
                return LoginHttpEndpointSync.EndpointResult(
                    LoginHttpEndpointSync.EndpointResultStatus.AUTH_ERROR,
                    ""
                )
            } else if (mIsServerError) {

                return LoginHttpEndpointSync.EndpointResult(
                    LoginHttpEndpointSync.EndpointResultStatus.SERVER_ERROR,
                    ""
                )
            } else if (mIsNetworkError) {
                throw NetworkErrorException()
            } else {
                return LoginHttpEndpointSync.EndpointResult(
                    LoginHttpEndpointSync.EndpointResultStatus.SUCCESS,
                    AUTH_TOKEN
                )
            }
        }

    }

    class AuthTokenCacheTd : AuthTokenCache {
        var mAuthToken: String = ""


        override fun cacheAuthToken(authToken: String) {
            mAuthToken = authToken

        }

        override fun getAuthToken(): String {
            return mAuthToken
        }

    }

 class EventBusPosterTd : EventBusPoster {
        var mEvent: Any by Delegates.notNull()
        var mInteractionsCount: Int = 0

        override fun postEvent(event: Any) {
            mInteractionsCount++
            mEvent = event
        }

    }

}




























