package com.example.test5doublesfundamentals

import com.example.test5doublesfundamentals.Users.User
import com.example.test5doublesfundamentals.Users.UsersCache
import com.example.test5doublesfundamentals.networking.NetworkErrorException
import com.example.test5doublesfundamentals.networking.UserProfileHttpEndpointSync
import com.example.test5doublesfundamentals.networking.UserProfileHttpEndpointSync.EndpointResult

/**
 * Created by Thomy.
 */
class FetchUserProfileCaseSync {

    enum class UseCaseResult {
        SUCCESS, FAILURE, NETWORK_ERROR
    }

    var mUserProfileHttpEndpointSync: UserProfileHttpEndpointSync

    var mUserCache: UsersCache

    constructor(
        userProfileHttpEndpointSync: UserProfileHttpEndpointSync,
        usersCache: UsersCache
    ) {

        mUserProfileHttpEndpointSync = userProfileHttpEndpointSync
        mUserCache = usersCache
    }

    fun fetchUserProfileSync(userId: String): UseCaseResult {
        val endpointResult: EndpointResult
        try {

            endpointResult = mUserProfileHttpEndpointSync.getUserProfile("")

            mUserCache.cacheUser(
                User(
                    userId,
                    endpointResult.getFullName(),
                    endpointResult.getImageUrl()
                )
            )

        } catch (e: NetworkErrorException) {
            return UseCaseResult.NETWORK_ERROR
        }
        if (isSuccessfulEndpointResult(endpointResult))
            mUserCache.cacheUser(
                User(
                    userId,
                    endpointResult.getFullName(),
                    endpointResult.getImageUrl()
                )
            )

        return UseCaseResult.SUCCESS
    }

    private fun isSuccessfulEndpointResult(endpointResult: EndpointResult): Boolean {
        return endpointResult.getStatus() == UserProfileHttpEndpointSync.EndpointResultSatus.SUCCESS
    }

}